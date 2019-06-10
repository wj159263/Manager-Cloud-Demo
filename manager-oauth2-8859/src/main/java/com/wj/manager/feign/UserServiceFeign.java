package com.wj.manager.feign;

import com.wj.manager.feign.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(serviceId = "manager-provider-user-7788",fallbackFactory = UserServiceFallback.class)
public interface UserServiceFeign {

    @GetMapping("/test/insert")
    public Object testGlobalTran();
}
