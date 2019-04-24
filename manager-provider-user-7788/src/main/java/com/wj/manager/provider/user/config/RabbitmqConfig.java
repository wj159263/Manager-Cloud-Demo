package com.wj.manager.provider.user.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitmqConfig中配置queue、exchange，在其他地方使用RabbitTemplate
 */
@Configuration
public class RabbitmqConfig {
    public static final String QUEUE_MSG = "queue_message_boot";
    public static final String QUEUE_EMAIL = "queue_email_boot";

    //#可以代表空、一个或者多个。channel.basicPublish()填发送规则，key.email匹配email，key.email.msg匹配2个
    public static final String ROUTING_KEY_EMAIL = "key.#.email.#";
    public static final String ROUTING_KEY_MSG = "key.#.msg.#";
    public static final String EXCHANGE_TOPIC = "my_exchange_topic_boot";


    @Bean(EXCHANGE_TOPIC)
    public Exchange EXCHANGE_TOPIC(){
        //声明交换机并持久化
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPIC).durable(true).build();
    }
    //声明队列
    @Bean(QUEUE_EMAIL)
    public Queue QUEUE_EMAIL(){
        return new Queue(QUEUE_EMAIL);
    }

    @Bean(QUEUE_MSG)
    public Queue QUEUE_MSG(){
        return new Queue(QUEUE_MSG);
    }

    @Bean
    public Binding bindingEmailMq(@Qualifier(QUEUE_EMAIL) Queue emailQueue,
                             @Qualifier(EXCHANGE_TOPIC) Exchange exchange){
        //bing队列，to交换机，with路由key，noargs
        return BindingBuilder.bind(emailQueue).to(exchange).with(ROUTING_KEY_EMAIL).noargs();
    }

    @Bean
    public Binding bindingMsgMq(@Qualifier(QUEUE_MSG) Queue msgQueue,
                                  @Qualifier(EXCHANGE_TOPIC) Exchange exchange){
        return BindingBuilder.bind(msgQueue).to(exchange).with(ROUTING_KEY_MSG).noargs();
    }


}
