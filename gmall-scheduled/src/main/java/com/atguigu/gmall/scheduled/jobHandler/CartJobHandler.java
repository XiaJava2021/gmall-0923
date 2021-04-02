package com.atguigu.gmall.scheduled.jobHandler;

import com.atguigu.gmall.scheduled.mapper.CartMapper;
import com.atguigu.gmall.scheduled.pojo.Cart;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import groovy.util.logging.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Slf4j
public class CartJobHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CartMapper cartMapper;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String EXCEPTION_KEY = "cart:exception";
    private static final String KEY_PREFIX = "cart:info:";

    @XxlJob("cartExceptionHandler")
    public ReturnT<String> exceptionHandler(String param){

        BoundSetOperations<String, String> setOps = this.redisTemplate.boundSetOps(EXCEPTION_KEY);

        String userId = setOps.pop();

        while (StringUtils.isNotBlank(userId)){
            // 先删除 mysql 中的对应用户的记录
            this.cartMapper.delete(new UpdateWrapper<Cart>().eq("user_id",userId));

            // 读取 redis 中记录
            BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(KEY_PREFIX + userId);
            List<Object> cartJsons = hashOps.values();

            if (!CollectionUtils.isEmpty(cartJsons)){
                // 新增到 mysql 中
                cartJsons.forEach(cartJson ->{
                    try {
                        Cart cart = MAPPER.readValue(cartJson.toString(), Cart.class);
                        this.cartMapper.insert(cart);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });
            }

            // 获取下一个用户
            userId = setOps.pop();
        }

        return ReturnT.SUCCESS;
    }
}
