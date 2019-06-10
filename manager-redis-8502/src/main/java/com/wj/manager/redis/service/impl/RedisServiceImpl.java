package com.wj.manager.redis.service.impl;

import com.wj.manager.redis.service.RedisService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void put(String key, String value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds);
    }

    @Override
    public Object get(String key) {
        //redisTemplate.expire

      /*  redisTemplate.watch("dd");
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.multi();
        Object o = redisTemplate.opsForValue().get(key);
        redisTemplate.exec();
        redisTemplate.unwatch();*/
        Object o = redisTemplate.opsForValue().get(key);
       return o;
    }

    public void test(){
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        value.set("dd","dd",17200);
        String dd = value.get("dd");
        System.out.println(dd);
        value.append("dd","cc");
         dd = value.get("dd");
        System.out.println(dd);
        Boolean aBoolean = value.setIfAbsent("dd", "cc11");
        System.out.println(aBoolean);
        dd = value.get("dd");
        System.out.println(dd);
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put("persion","name","张三");
        hash.put("persion","age","18");
        Set<Object> persion = hash.keys("persion");
        System.out.println(persion);
        List<Object> persion1 = hash.values("persion");
        System.out.println(persion1);
    }
}
