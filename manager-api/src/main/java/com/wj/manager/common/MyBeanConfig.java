/*package com.wj.manager.common;

import com.wj.manager.common.feign.fallback.BaseFallback;
import com.wj.manager.common.feign.fallback.DefaultFallback;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanConfig {
    @Bean
    @ConditionalOnMissingBean(BaseFallback.class)
    public BaseFallback defaultFallback(){
        return new DefaultFallback();
    }

}*/
