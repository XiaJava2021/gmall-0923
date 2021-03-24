package com.atguigu.gmall.index;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class GmallIndexApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testBloomFilter(){
        RBloomFilter<Object> bloomFilter = this.redissonClient.getBloomFilter("bloomFilter");
        bloomFilter.tryInit(20,0.3);
        bloomFilter.add("1");
        bloomFilter.add("2");
        bloomFilter.add("3");
        bloomFilter.add("4");
        bloomFilter.add("5");
        bloomFilter.add("6");
        bloomFilter.add("7");
        System.out.println(bloomFilter.contains("1"));
        System.out.println(bloomFilter.contains("3"));
        System.out.println(bloomFilter.contains("5"));
        System.out.println(bloomFilter.contains("7"));
        System.out.println(bloomFilter.contains("9"));
        System.out.println(bloomFilter.contains("11"));
    }

//    @PostConstruct
//    void init(){
//        redisTemplate.setValueSerializer(RedisSerializer.java());
//    }

    @Test
    void contextLoads() {
        this.redisTemplate.opsForValue().set("test1","迪丽热巴2");
        System.out.println(this.redisTemplate.opsForValue().get("test1"));
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("这是定时器初始化定时任务: " + System.currentTimeMillis());
            }
        },5000,10000);


//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
//        System.out.println("系统时间: " + System.currentTimeMillis());
//        scheduledExecutorService.scheduleAtFixedRate( () ->{
//            System.out.println("这是定时任务: " + System.currentTimeMillis());
//        },5,10, TimeUnit.SECONDS);
    }

}
