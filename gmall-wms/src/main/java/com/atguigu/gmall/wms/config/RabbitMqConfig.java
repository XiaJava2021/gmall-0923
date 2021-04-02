package com.atguigu.gmall.wms.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author Hasee  2021/3/19 18:31 周五
 * @version JDK 8.017
 * @description:
 */
@Slf4j
@Configuration
public class RabbitMqConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause)->{
            if (!ack){
                log.error("消息没有到达交换机,原因: {}",cause);
            }
        });
        this.rabbitTemplate.setReturnCallback(( message,  replyCode,  replyText,  exchange,  routingKey)->{
            log.error("消息没有到达队列. 交换机: {} , 路由键: {} , 消息内容: {}",exchange,routingKey,new String(message.getBody()));
        });
    }

    /**
     * 延时交换机: ORDER_EXCHANGE
     */


    /**
     * 延时队列: STOCK_TTL_QUEUE
     */
    @Bean
    public Queue ttlQueue(){
        return QueueBuilder.durable("ORDER_TTL_QUEUE")
                .withArgument("x-message-ttl",100000)
                .withArgument("x-dead-letter-exchange","ORDER_EXCHANGE")
                .withArgument("x-dead-letter-routing-key","stock.unlock")
                .build();
    }

    /**
     * 把延时队列绑定到延时交换机: stock.ttl
     */
    @Bean
    public Binding ttlBinding(){
        return new Binding("STOCK_TTL_QUEUE", Binding.DestinationType.QUEUE,"ORDER_EXCHANGE",
                "stock.ttl",null);
    }

    /**
     * 死信交换机: ORDER_EXCHANGE
     */


    /**
     * 死信队列: STOCK_UNLOCK_QUEUE
     */


    /**
     * 把死信队列绑定到死信交换机: stock.unlock
     */



}
