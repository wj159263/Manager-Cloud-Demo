package com.wj.manager.consumer.user.config.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class MqSender {
    private ObjectMapper mapper = new ObjectMapper();
    public static final String QUEUE_START = "start_order";
    public static final String QUEUE_FINISH = "finish_order";
    //#可以代表空、一个或者多个。channel.basicPublish()填发送规则，key.email匹配email，key.email.msg匹配2个
    public static final String ROUTING_KEY_START = "order.#.start.#";
    public static final String ROUTING_KEY_FINISH = "order.#.finish.#";
    public static final String EXCHANGE_topic = "order_exchange_topic";

    public void sendMsg(TaskBean taskBean,String exchange,String routingkey) throws Exception {
        //发送之前取数据库查下
        //taskBeanService.findByid(taskBean.getId()),的到数据进行判断，并设置更新时间
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        Connection connection= null;
        Channel channel = null;
        String data = mapper.writeValueAsString(taskBean);
        try {
            connection = factory.newConnection();
            //建立通道
            channel = connection.createChannel();

            channel.queueDeclare(QUEUE_START,true,false,false,null);
            channel.queueDeclare(QUEUE_FINISH,true,false,false,null);

            channel.exchangeDeclare(exchange, BuiltinExchangeType.TOPIC);


            channel.queueBind(QUEUE_START,exchange,ROUTING_KEY_START);
            channel.queueBind(QUEUE_FINISH,exchange,ROUTING_KEY_FINISH);

            channel.basicPublish(exchange,routingkey,null,data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            channel.close();
            connection.close();
        }

    }
}
