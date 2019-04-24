package com.wj.manager.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    public static final ObjectMapper mapper = new ObjectMapper();

    public static <T>T json2Object(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }
    public static String object2Json(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}
