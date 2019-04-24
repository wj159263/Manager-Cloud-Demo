/*
package com.wj.manager.consumer.user.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = {RabbitmqConfig.QUEUE_EMAIL})
public class RabbitmqConsumer {
    @RabbitHandler
    public void handleMsg(String context){
        System.out.println(context);
    }
}
*/
