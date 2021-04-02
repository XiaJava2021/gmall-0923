package com.atguigu.gmall.cart.config;

import com.atguigu.gmall.cart.exceptionHandler.UncaughtExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.Executor;

/**
 * @author Hasee  2021/3/29 15:18 周一
 * @version JDK 8.017
 * @description:
 */
@Configuration
public class ExceptionConfig implements AsyncConfigurer {

    @Autowired
    private UncaughtExceptionHandler uncaughtExceptionHandler;

    /**
     * 配置线程池
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        return null;
    }


    /**
     * 配置统一的异常处理器
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return uncaughtExceptionHandler;
    }
}
