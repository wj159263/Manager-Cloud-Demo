package com.wj.manager.consumer.test;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者和消费者最好都声明队列，名称相同就好
 */
public class MqProducerFanoutTest {
    public static final String QUEUE_MSG = "QUEUE_message";
    public static final String QUEUE_EMAIL = "QUEUE_email";
    public static final String EXCHANGE_FANOUT = "exchange_fanout";
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
            channel.queueDeclare(QUEUE_MSG,true,false,false,null);
            channel.queueDeclare(QUEUE_EMAIL,true,false,false,null);


            /**
             * 1、交换机名称
             * 2、交换机类型
             * FANOUT:publish/subscribe
             * direct:对应路由工作模式
             * topic:topic工作模式
             * headers:
             */
            //交换机声明
            channel.exchangeDeclare(EXCHANGE_FANOUT, BuiltinExchangeType.FANOUT);


            /**
             * 1、队列名称:
             * 2、交换机名称:
             * 3、路由key，作用是交换机根据路由key将消息发送到指定的队列中，在发布/订阅模式为空字符串
             */
            //把队列和交换机绑定一起
            channel.queueBind(QUEUE_MSG,EXCHANGE_FANOUT,"");
            channel.queueBind(QUEUE_EMAIL,EXCHANGE_FANOUT,"");


            String msg = "FANOUT 你好2";
            /**
             * 参数明细
             * 1、交换机
             * 2、路由key，使用默认交换机时，路由key为队列名称
             * 3、消息属性
             * 4、消息内容
             */
            //发送消息
            channel.basicPublish(EXCHANGE_FANOUT,"",null,msg.getBytes());
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
