package com.wj.manager.controller;

import com.wj.manager.common.entity.SysUser;
import com.wj.manager.common.exception.CustomException;
import com.wj.manager.dto.AuthToken;
import com.wj.manager.dto.ResponseResult;
import com.wj.manager.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
    @Autowired
    AuthService authService;

    @GetMapping("/test/gtran")
    public Object testAlibabaGrobalTransaction(){
        authService.testAlibabaGrobalTransaction();
        return "sucess++";
    }

    @GetMapping("/test/g")
    public Object tessaction(){
        return "sucess++";
    }

    @PostMapping("/auth/login")
    public ResponseResult login(@ModelAttribute SysUser loginUser, HttpServletRequest request){
        if(loginUser == null || StringUtils.isBlank(loginUser.getAccount())|| StringUtils.isBlank(loginUser.getPassword())){
            throw new RuntimeException("ee");
        }
        //获取令牌
        AuthToken token = null;
        token = authService.login(loginUser.getAccount(), loginUser.getPassword());

        if(token != null && StringUtils.isNoneBlank(token.getJwtToken()) && StringUtils.isNoneBlank(token.getRefreshToken())){
            return ResponseResult.success(token);
        }
        throw new  RuntimeException("ee");
    }

    @GetMapping("/auth/logout")
    public ResponseResult logout(HttpServletRequest request){
        authService.testProgramTran();
       return ResponseResult.success("退出登陆成功");
    }

    @GetMapping("/auth/unlock")
    public ResponseResult unlock(String password){
        System.out.println(authService.getClass().getName());
        authService.testTran();
            return ResponseResult.success();
    }

}
