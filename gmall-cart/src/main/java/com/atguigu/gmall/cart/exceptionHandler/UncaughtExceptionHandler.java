package com.atguigu.gmall.cart.exceptionHandler;

import com.atguigu.gmall.cart.interceptors.LoginInterceptor;
import com.atguigu.gmall.cart.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Hasee  2021/3/29 15:13 周一
 * @version JDK 8.017
 * @description:
 */
@Component
@Slf4j
public class UncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String EXCEPTION_KEY = "cart:exception";

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        // 输出错误日志,或者记录到数据库
        log.error("异步执行出错.方法:{}, 参数:{}, 异常信息:{}",method.getName(), Arrays.asList(params),ex.getMessage());

        // 此处不能使用拦截器中的 ThreadLocal 获取用户的登录信息,因为这是子线程任务
        // UserInfo userInfo = LoginInterceptor.getUserInfo();
        BoundSetOperations<String, String> setOps = this.redisTemplate.boundSetOps(EXCEPTION_KEY);
        setOps.add(params[0].toString());

    }
}
