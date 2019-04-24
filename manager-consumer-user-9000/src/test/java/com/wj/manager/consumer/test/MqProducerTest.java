package com.wj.manager.consumer.test;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 原始的方法开发mq
 * 生产者和消费者最好都声明队列和交换机，名称相同就好
 */
public class MqProducerTest {
    public static final String QUEUE = "hello mq";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        Connection connection= null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            //建立通道
            channel = connection.createChannel();


            /**
             * 第一个参数:队列名称
             * 第二个参数:是否持久化，重启后消息队列还存在
             * 第三个参数:独占连接，队列只允许在该connection中访问，该connection以关闭队列就自动删除
             * 第四个参数:不用队列时，是否自动删除队列。当第3和第4为true时，队列就是临时队列
             * 第五个参数:拓展参数。比如设置队列存活时间
             */
            //通道中声明队列
            channel.queueDeclare(QUEUE,true,false,false,null);
            String msg = "rabbitmq 你好2";
            /**
             * 参数明细
             * 1、交换机，为空字符串时使用默认的交换机
             * 2、路由key，使用默认交换机时，路由key为队列名称
             * 3、消息属性
             * 4、消息内容
             */
            //发送消息
            channel.basicPublish("",QUEUE,null,msg.getBytes());
            System.out.println("rabbit mq 发送消息.........");
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
