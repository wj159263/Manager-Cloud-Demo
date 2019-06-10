package com.wj.manager.provider.user.config;

import com.rabbitmq.client.Channel;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class RabbitmqReceiver {
   /* @RabbitListener(queues = {RabbitmqConfig.QUEUE_EMAIL})
    public void handleDelivery(String msg, Message message, Channel channel) throws UnsupportedEncodingException {
        System.out.println("msg参数：："+ msg);
        System.out.println("message参数：："+ new String(message.getBody(),"utf-8"));
    }*/
}
