package com.wj.manager.common.feign.fallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wj.manager.common.dto.BaseResult;
import com.wj.manager.common.enumator.HttpStatesEnum;
import com.wj.manager.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
public class DefaultFallback implements BaseFallback{

    @Override
    public  String BadGateway() throws JsonProcessingException {
        List<BaseResult.Error> errors = new ArrayList<>();
        errors.add(new BaseResult.Error(String.valueOf(HttpStatesEnum.BAD_GATEWAY.getStatus()),HttpStatesEnum.BAD_GATEWAY.getContent()));
        return JsonUtil.object2Json(BaseResult.notOk(errors));
    }
}
