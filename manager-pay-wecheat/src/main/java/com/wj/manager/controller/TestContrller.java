package com.wj.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestContrller {
    @GetMapping("/to/{page}")
    public String goPage(@PathVariable("page")  String page){
        return page;
    }
    @GetMapping
    public String goPage11(Model model){
        model.addAttribute("data1","data111");
        model.addAttribute("data3","data333");
        model.addAttribute("data2","dataer二二");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute("data5","dataaaa5");
        RequestContextHolder.currentRequestAttributes().setAttribute("data4","data444",1);
        return "/pay";
    }


    @GetMapping("/print")
    @ResponseBody
    public Object print(){
        return "test data";
    }
}
