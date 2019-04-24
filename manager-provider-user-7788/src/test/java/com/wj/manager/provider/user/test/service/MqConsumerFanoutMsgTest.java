package com.wj.manager.provider.user.test.service;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqConsumerFanoutMsgTest {
    public static final String QUEUE_MSG = "QUEUE_message";
    public static final String EXCHANGE_FANOUT = "exchange_fanout";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            //建立通道
            channel = connection.createChannel();

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
             * 第一个参数:队列名称
             * 第二个参数:是否持久化，重启后消息队列还存在
             * 第三个参数:独占连接，队列只允许在该connection中访问，该connection以关闭队列就自动删除
             * 第四个参数:不用队列时，是否自动删除队列。当第3和第4为true时，队列就是临时队列
             * 第五个参数:拓展参数。比如设置队列存活时间
             */
            //通道中声明队列
            channel.queueDeclare(QUEUE_MSG, true, false, false, null);

            /**
             * 1、队列名称:
             * 2、交换机名称:
             * 3、路由key，作用是交换机根据路由key将消息发送到指定的队列中，在发布/订阅模式为空字符串
             */
            //把队列和交换机绑定一起
            channel.queueBind(QUEUE_MSG,EXCHANGE_FANOUT,  "");

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
                    System.out.println("QUEUE_MSG 接收消息.........");
                    System.out.println(exchange+"::"+deliveryTag+"::" + msg);
                }
            };
            /**
             * 参数：
             * 1、队列名称
             * 2、接收消息后是否自动回复(true自动回复，队列会删除消息。false反之)
             * 3、拿到消息该干什么
             */
            //接收消息
            channel.basicConsume(QUEUE_MSG,true,consumer);

        } catch (Exception e) {

        }
    }
}
