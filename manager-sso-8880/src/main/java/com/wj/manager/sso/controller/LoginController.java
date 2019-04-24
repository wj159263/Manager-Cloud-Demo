package com.wj.manager.sso.controller;

import com.wj.manager.common.entity.SysUser;
import com.wj.manager.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String toLoginPage(){
        return "login";
    }
    @PostMapping("/handle/login")
    public String handleLogin(String accound,String password){
        SysUser login = loginService.login(accound, password);
        System.out.println(login);
        return "ok";
    }

    @GetMapping("/handle/get")
    public String handleLogin(String key){
        String login = loginService.get(key);
        System.out.println(login);
        return "ok";
    }
}
