package com.atguigu.gmall.index.aspect;

import org.springframework.transaction.TransactionDefinition;

import java.lang.annotation.*;

/**
 * @author Hasee  2021/3/22 20:55 周一
 * @version JDK 8.017
 * @description:
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
//@Inherited      // 不可继承
@Documented
public @interface GmallCache {


    /**
     * 缓存 key 的前缀
     * @return
     */
    String prefix() default "";

    /**
     * 缓存的过期时间,单位: min
     * 默认一天
     * @return
     */
    int timeout() default 1440;


    /**
     * 防止缓存雪崩,给缓存时间添加随机值范围,单位: min
     * 默认 50 min
     * @return
     */
    int random() default 50;

    /**
     * 为了防止缓存的击穿,添加分布式锁,可以指定锁前缀
     * 默认: lock:
     * @return
     */
    String lock() default "lock:";

}
