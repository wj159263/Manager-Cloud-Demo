package com.wj.manager.consumer.user.config.mq;

import com.wj.manager.consumer.user.config.mq.service.TaskBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class TaskTest {
    @Autowired
    TaskBeanService taskBeanService;
    @Autowired
    MqSender mqSender;

    @Scheduled(cron = "0/3 * * * * ?")
    public void sendOrderTask() throws Exception {
        //查新一分钟前的数据
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE) - 1);

        List<TaskBean> taskBeanList = taskBeanService.getTaskBeanList(calendar.getTime(), 10);
        System.out.println(taskBeanList);
        for (TaskBean bean : taskBeanList){
            /*可能manager-consumer-user-9000部署了多台，当多台都查到taskBeanList相同时，执行
            taskBeanService.modifyVersion(bean)根据当前version去数据库version+1，这样后来的
            服务器就不会重复发送消息到rabbitmq了
            */
            if(taskBeanService.modifyVersion(bean) > 0) {
                mqSender.sendMsg(bean, bean.getMq_exchange(), bean.getRouting_key());
            }
        }
    }

    //@Scheduled(cron = "0/2 * * * * ?")
    public void pushDataScheduled(){
        System.out.println("start push 大苏打data scheduled111!::::+++"+Thread.currentThread().getName());
    }

    //@Scheduled(cron = "0/2 * * * * ?")
    public void pushDataScheduled2(){
        System.out.println("start push 大苏打data scheduled2222!::::+++"+Thread.currentThread().getName());
    }
}
