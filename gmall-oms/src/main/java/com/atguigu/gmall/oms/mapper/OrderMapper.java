package com.atguigu.gmall.oms.mapper;

import com.atguigu.gmall.oms.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 订单
 * 
 * @author xiatian
 * @email xiatian@atguigu.com
 * @date 2021-03-08 19:36:09
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {

    public Integer updateStatus(@Param("orderToken") String orderToken,@Param("expect") Integer expect,@Param("target") Integer target);
	
}
