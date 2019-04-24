package com.wj.manager.redis.service;

public interface RedisService {

    public void put(String key, String value, long seconds);

    public Object get(String key);
}
