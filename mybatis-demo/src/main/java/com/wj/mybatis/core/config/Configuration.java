package com.wj.mybatis.core.config;

import com.wj.mybatis.core.domain.MapperStatement;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private String jdbcUrl;
    private String driverClass;
    private String username;
    private String password;
    Map<String,MapperStatement> statementMap;
    private Configuration(){
        statementMap = new HashMap<String,MapperStatement>();
    }
    public static Configuration getInstance(){
        return StatementHelper.instance;
    }
    static class StatementHelper{
       private final static Configuration  instance= new Configuration();
    }
}
