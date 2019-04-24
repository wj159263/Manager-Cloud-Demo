package com.wj.manager.sso.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wj.manager.common.entity.SysUser;
import com.wj.manager.common.util.JsonUtil;
import com.wj.manager.sso.feign.RedisServiceFeign;
import com.wj.manager.sso.mapper.SysUserMapper;
import com.wj.manager.sso.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;

@Service
public class LoginServiceImpl implements LoginService {
    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    RedisServiceFeign redisServiceFeign;


    @Override
    public SysUser login(String account, String password) {
        if(StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            //todo
        }
        SysUser user = null;
        String redisUser = redisServiceFeign.get(account);
        if(StringUtils.isBlank(redisUser)){
            Example example = new Example(SysUser.class);
            //第一个参数是entity的字段
            example.createCriteria().andEqualTo("account",account);
            user = sysUserMapper.selectOneByExample(example);
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(password,user.getPassword())){
                logger.info("测试： sso ：：密码正确");
            }
            String json = null;
            try {
                json = JsonUtil.object2Json(user);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            redisServiceFeign.put(account,json,7200);
        }else {
            try {
                user = JsonUtil.json2Object(redisUser, SysUser.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public String get(String key) {
        return redisServiceFeign.get(key);
    }
}
