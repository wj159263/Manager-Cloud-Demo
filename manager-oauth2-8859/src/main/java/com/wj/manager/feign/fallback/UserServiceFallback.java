package com.wj.manager.feign.fallback;

import com.wj.manager.feign.UserServiceFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallback implements FallbackFactory<UserServiceFeign>{
    @Override
    public UserServiceFeign create(Throwable throwable) {
        return new UserServiceFeign(){

            @Override
            public Object testGlobalTran() {
                return "熔断了，操作失败";
            }
        };
    }
}
