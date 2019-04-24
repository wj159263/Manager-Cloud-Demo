package com.wj.manager.consumer.user.config.mq.service;

import com.wj.manager.consumer.user.config.mq.MqSender;
import com.wj.manager.consumer.user.config.mq.TaskBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class TaskBeanServiceImpl implements TaskBeanService {
    @Override
    public List<TaskBean> getTaskBeanList(Date updatetime, int size) {
        //应该取数据库查updatetime之前的数据，size：一页记录数



        List<TaskBean> list = new ArrayList<>();
        TaskBean taskBean1 = new TaskBean();
        TaskBean taskBean2 = new TaskBean();
        taskBean1.setId(1);
        taskBean1.setCreatetime(new Date());
        taskBean1.setUpdatetime(new Date());
        taskBean1.setDeletetime(new Date());
        taskBean1.setMq_exchange(MqSender.EXCHANGE_topic);
        taskBean1.setRouting_key(MqSender.ROUTING_KEY_START);
        String body1 = "{\"userId\":\"49\",\"orderId \":\"asasdasf\"}";
        taskBean1.setRequest_body(body1);

        taskBean2.setId(2);
        taskBean2.setCreatetime(new Date());
        taskBean2.setUpdatetime(new Date());
        taskBean2.setDeletetime(new Date());
        taskBean2.setMq_exchange(MqSender.EXCHANGE_topic);
        taskBean2.setRouting_key(MqSender.ROUTING_KEY_START);
        String body2 = "{\"userId\":\"50\",\"orderId\":\"asasdasf11\"}";
        taskBean2.setRequest_body(body2);

        list.add(taskBean1);
        list.add(taskBean2);
        return list;
    }

    @Override
    public void modifyUpdatatime(TaskBean taskBean, Date date) {
        taskBean.setUpdatetime(date);
    }

    @Override
    public int modifyVersion(TaskBean taskBean) {
        //正真应该取数据库操作
        //update tb_taskbean set version=version+1 where id= #{id} and version = #{version}
        return 1;
    }
}
