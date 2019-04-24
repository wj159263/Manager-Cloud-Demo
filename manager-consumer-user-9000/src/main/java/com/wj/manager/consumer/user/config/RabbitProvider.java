/*
package com.wj.manager.consumer.user.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class RabbitProvider {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendEmail(){
        String email = "springboot rabbitmq 发送email消息";
        */
/**
         * 参数
         * 交换机
         * routingkey
         * 消息内容
         *//*

        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPIC,
                "key.email",email);
    }
}
*/
