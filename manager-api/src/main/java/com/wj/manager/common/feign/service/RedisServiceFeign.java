package com.wj.manager.common.feign.service;

import com.wj.manager.common.feign.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "MANAGER-REDIS-8502", fallbackFactory = RedisServiceFallback.class)
public interface RedisServiceFeign {

    @PostMapping("/put")
    public String put(@RequestParam("key") String key, @RequestParam("value") String value,
                      @RequestParam("seconds") long seconds);

    @GetMapping("/get")
    public String get(String key);
}
