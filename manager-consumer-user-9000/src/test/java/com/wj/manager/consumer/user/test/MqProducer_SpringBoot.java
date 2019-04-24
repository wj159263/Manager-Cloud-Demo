package com.wj.manager.consumer.user.test;

import com.wj.manager.consumer.user.UserConsumer9000_App;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserConsumer9000_App.class)
public class MqProducer_SpringBoot {
  /*  @Autowired
    RabbitProvider rabbitProvider;

    @Test
    public void testSendEmail(){
        rabbitProvider.sendEmail();
    }*/
}
