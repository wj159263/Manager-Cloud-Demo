package com.wj.manager.provider.user.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wj.manager.common.entity.SysUser;
import com.wj.manager.provider.user.mapper.SysUserMapper;
import com.wj.manager.provider.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {
    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SysUserMapper sysUserMapper;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    @LcnTransaction
    @Transactional
    public Object insert() {
        SysUser user = new SysUser();
        user.setPassword("111111");
        user.setAccount("ddddd");
        user.setName("孔明");
        sysUserMapper.insert(user);
        int a= 1/0;
        return null;
    }

    @Override
    @Transactional
    public void register(SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        sysUserMapper.insert(user);
    }

    @Override
    public SysUser login(String account, String password) {

        Example example = new Example(SysUser.class);
        //第一个参数是entity的字段
        example.createCriteria().andEqualTo("account",account);
        SysUser user = sysUserMapper.selectOneByExample(example);
        if(passwordEncoder.matches(password,user.getPassword())){
            logger.info("测试：：：密码正确");
        }
        return user;
    }
}
