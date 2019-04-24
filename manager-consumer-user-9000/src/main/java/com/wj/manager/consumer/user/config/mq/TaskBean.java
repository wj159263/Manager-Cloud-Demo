package com.wj.manager.consumer.user.config.mq;


import lombok.Data;

import java.util.Date;

/**
 * rabbitmq发送消息的实体类
 * 应该在数据库建表和该类对应起来
 */
@Data
public class TaskBean {
    private int id;
    private Date createtime;
    private Date updatetime;
    private Date deletetime;
    private int tasktype;
    private String mq_exchange; //rabbitmq交换机
    private String routing_key;
    private String request_body;
    private int version;
    private int status;
    private String error_msg;
}
