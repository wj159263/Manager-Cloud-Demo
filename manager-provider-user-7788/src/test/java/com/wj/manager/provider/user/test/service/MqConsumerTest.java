package com.wj.manager.provider.user.test.service;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqConsumerTest {
    public static final String QUEUE = "hello mq";
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        Connection connection= null;
        try {
            connection = factory.newConnection();
            //建立通道
            Channel channel = connection.createChannel();

            /**
             * 第一个参数:队列名称
             * 第二个参数:是否持久化，重启后消息队列还存在
             * 第三个参数:独占连接，队列只允许在该connection中访问，该connection以关闭队列就自动删除
             * 第四个参数:不用队列时，是否自动删除队列。当第3和第4为true时，队列就是临时队列
             * 第五个参数:拓展参数。比如设置队列存活时间
             */
            //通道中声明队列
            channel.queueDeclare(QUEUE,true,false,false,null);


            DefaultConsumer consumer = new DefaultConsumer(channel){

                //接收消息后调用该方法

                /**
                 *
                 * @param consumerTag 消费者标签
                 * @param envelope 信封，可以获取交换机
                 * @param properties 消息属性
                 * @param body 消息内容
                 * @throws IOException
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String exchange = envelope.getExchange();//交换机
                    long deliveryTag = envelope.getDeliveryTag();//消息id
                    String msg = new String(body,"utf-8");
                    System.out.println("rabbit mq 接收消息.........");
                    System.out.println(exchange+"::"+deliveryTag+"::"+msg);
                }
            };
            /**
             * 参数：
             * 1、队列名称
             * 2、接收消息后是否自动回复(true自动回复，队列会删除消息。false反之)
             * 3、拿到消息该干什么
             */
            //接收消息
            channel.basicConsume(QUEUE,true,consumer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }
}
