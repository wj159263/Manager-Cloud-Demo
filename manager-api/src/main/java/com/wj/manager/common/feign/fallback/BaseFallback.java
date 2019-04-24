package com.wj.manager.common.feign.fallback;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface BaseFallback {
    public  String BadGateway() throws JsonProcessingException;
}
