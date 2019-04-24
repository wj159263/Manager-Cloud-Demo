package com.wj.manager.redis.service.impl;

import com.wj.manager.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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
        Object o = redisTemplate.opsForValue().get(key);
       return o;
    }
}
