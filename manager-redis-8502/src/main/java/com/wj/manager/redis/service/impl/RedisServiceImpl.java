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
      @Override
    /**
     * redis使用lua脚本
     * 脚本运行期间出错不会回滚
     */
    public Object userScript(){
        Random random = new Random();
        byte[] bytes = "gaskdmgfj223".getBytes();
        int length = bytes.length;
        byte[] bytes1 = new byte[2];
        for (int j = 0; j < 2; j++) {
            bytes1[j] = bytes[random.nextInt(length)];
        }
        String name = new String(bytes1);
        int i = random.nextInt(27);
        System.out.println("123");

        //cjson.decode
        //redis使用lua：
        String scriptText = "local nowval = redis.call('hincrby',KEYS[1],KEYS[2],1);\n" +
                "local temp1 = ARGV[1];" +
                "if type(nowval) == 'string' then \n" +
                "nowval = tonumber(nowval); \n" +
                "end \n" +
                "if type(temp1) == 'string' then \n" +
                "temp1 = tonumber(temp1); \n" +
                "end \n" +
                "if nowval > temp1 then \n" +
                "local user1 = {};" +
                "user1['name'] = ARGV[2]; \n" +
                "user1['age'] = ARGV[3]; \n" +
                "local x = cjson.encode(user1);" +
                "redis.call('lpush',KEYS[3],x); \n" +
                "return x;" +
                "else \n" +
                "return 0; \n" +
                "end";
        DefaultRedisScript<List> redisScript = new DefaultRedisScript<List>();
        //Long\Boolean\list\DeSerialized之一。写Object的话，返回是默认List
        redisScript.setResultType(List.class);
        redisScript.setScriptText(scriptText);
        List<String> keys = new ArrayList<String>();
        keys.add("map");
        keys.add("key1");
        keys.add("user_list");
        Object[] arg = {5,name,i};
        Object execute = redisTemplate.execute(redisScript, keys, arg);

        String typeName = execute.getClass().getTypeName();
        System.out.println(execute.getClass().getTypeName());
        System.out.println(execute);
        return execute;
    }
}
