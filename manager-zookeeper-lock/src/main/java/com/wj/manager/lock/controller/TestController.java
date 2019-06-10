package com.wj.manager.lock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    int x =0;
    @GetMapping("/test")
    public void ss(){
        x++;
        System.out.println("x的值："+x);
    }
}
