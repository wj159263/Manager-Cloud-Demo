package com.wj.manager.consumer.user.service.feign;

import com.wj.manager.common.dto.BaseResult;
import com.wj.manager.common.enumator.HttpStatesEnum;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceFeignFallback implements FallbackFactory<UserServiceFeign> {

    @Override
    public UserServiceFeign create(Throwable throwable) {
        return new UserServiceFeign() {
            @Override
            public BaseResult login(String account, String password) {
                List<BaseResult.Error> errors = new ArrayList<>();
                errors.add(new BaseResult.Error(String.valueOf(HttpStatesEnum.BAD_GATEWAY.getStatus()),HttpStatesEnum.BAD_GATEWAY.getContent()));
                return BaseResult.notOk(errors);
            }
        };
    }
}
