package com.wj.manager.provider.user.test.service;

import com.wj.manager.common.entity.SysUser;
import com.wj.manager.provider.user.UserProvider7788_App;
import com.wj.manager.provider.user.mapper.SysUserMapper;
import com.wj.manager.provider.user.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserProvider7788_App.class)
public class UserServiceTest {
    @Autowired
    SysUserMapper userMapper;

    @Autowired
    UserService userService;

    @Test
    public void login(){
        SysUser boss = userService.login("boss", "111111");
        Assert.assertNotNull(boss);
    }

    @Test
    public void register(){
        SysUser user = new SysUser();
        user.setAccount("888888111");
        user.setPassword("111111");
        userService.register(user);
        //SysUser sysUser = userMapper.selectByPrimaryKey(1);
       // System.out.println(sysUser.getAccount());
    }
}
