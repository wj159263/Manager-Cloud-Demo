package com.wj.manager.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wj.manager.common.dto.BaseResult;
import com.wj.manager.redis.controller.RedisController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =RedisApplication6379_App.class)
public class RedisTest {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    RedisController controller;
    @Test
    public void test11() throws Exception{
        redisTemplate.opsForValue().set("aa","123123",5, TimeUnit.MINUTES);
        Long aa = redisTemplate.getExpire("aa");

        String aa1 = redisTemplate.opsForValue().get("aa");
        ObjectMapper mapper = new ObjectMapper();
        try {
            BaseResult result = BaseResult.ok("撒大苏打");

            String asString = mapper.writeValueAsString(result);
            redisTemplate.opsForValue().set("controller",asString,5, TimeUnit.MINUTES);
            String ss = redisTemplate.opsForValue().get("controller");
            BaseResult redisController = mapper.readValue(ss, BaseResult.class);
            System.out.println(redisController);

        } catch (Exception e) {
            e.printStackTrace();
        }
        redisTemplate.opsForValue().set("bb","3123");
        Long bb = redisTemplate.getExpire("bb");
        redisTemplate.expire("bb",15,TimeUnit.MINUTES);
        Long bb1 = redisTemplate.getExpire("bb");
        redisTemplate.persist("bb"); //清除过期时间
        Long bb12 = redisTemplate.getExpire("bb");
        redisTemplate.delete("bb");
        Boolean bb2 = redisTemplate.hasKey("bb");
        String bb3 = redisTemplate.opsForValue().get("bb");

        redisTemplate.opsForHash().put("cc","c1","11");
        redisTemplate.opsForHash().put("cc","c2","2");
        redisTemplate.opsForHash().put("cc","c3","3");
        Object o = redisTemplate.opsForHash().get("cc", "c1");
        redisTemplate.opsForHash().increment("cc","c1",-2);
        Object o21 = redisTemplate.opsForHash().get("cc", "c1");

        Map<Object, Object> cc = redisTemplate.opsForHash().entries("cc");

        List<Object> reslutMapList=redisTemplate.opsForHash().values("cc");
        Set<Object> resultMapSet=redisTemplate.opsForHash().keys("cc");
        redisTemplate.expire("cc",5,TimeUnit.MINUTES);
        Long cc1 = redisTemplate.getExpire("cc");


        Map<String,String> map=new HashMap<String,String>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        map.put("key5","value5");
        redisTemplate.opsForHash().putAll("map",map);
        Object o2 = redisTemplate.opsForHash().get("map1", "key3");
        Map<Object, Object> cc12 = redisTemplate.opsForHash().entries("map");

        List<Object> reslutMapList1=redisTemplate.opsForHash().values("map");
        Set<Object> resultMapSet1=redisTemplate.opsForHash().keys("map");

    }
}
