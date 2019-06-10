package com.wj.manager.provider.user.controller;

import com.wj.manager.common.dto.BaseResult;
import com.wj.manager.common.entity.SysUser;
import com.wj.manager.provider.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/test/insert")
    public Object testGlobalTran(){
        userService.insert();
        return "success";
    }

    @GetMapping("/login")
    public BaseResult login(String account ,String password){
        System.out.println("controller:"+userService.getClass().getName());
        BaseResult baseResult = checkLogin(account, password);
        if(baseResult != null){
            return baseResult;
        }

        SysUser login = userService.login(account, password);
        if (login != null && StringUtils.isNoneBlank(login.getAccount())){
            return BaseResult.ok(login);
        }else {
            List list = new ArrayList<>();
            list.add(new BaseResult.Error("","登陆失败"));
            return BaseResult.notOk(list);
        }


    }



    private BaseResult checkLogin(String account ,String password){

        List<BaseResult.Error> erros = new ArrayList();
        if (StringUtils.isBlank(account)){
            BaseResult.Error error = new BaseResult.Error();
            error.setField("account");
            error.setMessage("账号不能为空");
            erros.add(error);
        }
        if (StringUtils.isBlank(password)){
            BaseResult.Error error = new BaseResult.Error();
            error.setField("password");
            error.setMessage("密码不能为空");
            erros.add(error);
        }
        if(StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return BaseResult.notOk(erros);
        }
        return null;
    }
}
