package com.wj.manager.consumer.user.service.feign;

import com.wj.manager.common.dto.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "manager-provider-user-7788",fallbackFactory = UserServiceFeignFallback.class)
public interface UserServiceFeign {
    //feign的接口中多个参数的话，必须加上@RequestParam，不然报错 Method has too many Body parameters
    @GetMapping("/login")
    public BaseResult login(@RequestParam("account") String account , @RequestParam("password") String password);
}
