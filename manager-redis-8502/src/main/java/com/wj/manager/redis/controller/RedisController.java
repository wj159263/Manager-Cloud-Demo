package com.wj.manager.redis.controller;

import com.wj.manager.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    RedisService redisService;

    @PostMapping("/put")
    public String put(String key, String value, long seconds){
        redisService.put(key, value, seconds);
        return "ok";
    }
    @GetMapping("/get")
    public String get(String key){
       return String.valueOf( redisService.get(key));
    }
}
