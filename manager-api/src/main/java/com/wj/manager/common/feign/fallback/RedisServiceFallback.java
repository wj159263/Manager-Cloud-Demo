package com.wj.manager.common.feign.fallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wj.manager.common.dto.BaseResult;
import com.wj.manager.common.enumator.HttpStatesEnum;
import com.wj.manager.common.feign.service.RedisServiceFeign;
import com.wj.manager.common.util.JsonUtil;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RedisServiceFallback implements FallbackFactory<RedisServiceFeign> {
    final Logger logger = LoggerFactory.getLogger(getClass());

    //BaseFallback baseFallback = new DefaultFallback();


    @Override
    public RedisServiceFeign create(Throwable throwable) {
        return new RedisServiceFeign(){
            @Override
            public String put(String key, String value, long seconds) {
                try {
                    List<BaseResult.Error> errors = new ArrayList<>();
                    errors.add(new BaseResult.Error(String.valueOf(HttpStatesEnum.BAD_GATEWAY.getStatus()),HttpStatesEnum.BAD_GATEWAY.getContent()));
                    return JsonUtil.object2Json(BaseResult.notOk(errors));

                } catch (JsonProcessingException e) {
                    logger.error(e.getOriginalMessage());
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public String get(String key) {
                try {
                    List<BaseResult.Error> errors = new ArrayList<>();
                    errors.add(new BaseResult.Error(String.valueOf(HttpStatesEnum.BAD_GATEWAY.getStatus()),HttpStatesEnum.BAD_GATEWAY.getContent()));
                    return JsonUtil.object2Json(BaseResult.notOk(errors));

                } catch (JsonProcessingException e) {
                    logger.error(e.getOriginalMessage());
                    e.printStackTrace();
                }
                return null;
            }
        };
    }
}
