package com.wj.manager.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RedisServiceImpl2 {
    @Autowired
    RedisTemplate redisTemplate;

    public void test(){
        Persion zs = new Persion("张三21", 1137);
        redisTemplate.opsForValue().set("kk",zs);
        Object zs1 = redisTemplate.opsForValue().get("kk");
        System.out.println(zs1);
       /* redisTemplate.boundValueOps("myname").set("wj");
        Object myname = redisTemplate.boundValueOps("myname").get();
        System.out.println(myname);

        redisTemplate.opsForValue().set("yourname","jw",72000);
        Object yourname = redisTemplate.opsForValue().get("yourname");
        System.out.println(yourname);

        SetOperations set = redisTemplate.opsForSet();
        set.add("hobbit","足球","篮球","kkkbo");
        Set hobbit = set.members("hobbit");
        System.out.println(hobbit);*/

    }


}
