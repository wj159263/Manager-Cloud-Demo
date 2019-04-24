package com.wj.manager.consumer.user.config.mq.service;

import com.wj.manager.consumer.user.config.mq.TaskBean;

import java.util.Date;
import java.util.List;

public interface TaskBeanService {
    public List<TaskBean> getTaskBeanList(Date updatetime, int size);

    public void modifyUpdatatime(TaskBean taskBean,Date date);
    //public int modifyVersion(int taskBeanId, int version);->update tb_taskbean set version=version+1 where id= #{id} and version = #{version}
    public int modifyVersion(TaskBean taskBean);
}
