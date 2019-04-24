package com.wj.manager.consumer.user.controller;

import com.wj.manager.common.dto.BaseResult;
import com.wj.manager.consumer.user.service.feign.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    UserServiceFeign userServiceFeign;

    @GetMapping(value = {"","login"})
    public String login(){
        return "index";
    }

    @GetMapping("/to/{page}")
    public String goPage(@PathVariable("page") String page){
        return page;
    }
    @ResponseBody
    @GetMapping("/handle/login")
    public BaseResult handleLogin(String account,String password){
        BaseResult login = userServiceFeign.login(account, password);
        return login;
    }

    @ResponseBody
    @GetMapping("/handle/test")
    @PreAuthorize("hasAuthority('/user')")
    public String handleTest(){
        return "/handle/test//////";
    }

    @ResponseBody
    @GetMapping("/handle/test1")
    @PreAuthorize("hasAuthority('/u')")
    public String handleTest1(){
        return "/handle/test11";
    }
}
