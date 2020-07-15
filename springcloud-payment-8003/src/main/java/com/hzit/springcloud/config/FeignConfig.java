package com.hzit.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author biXia
 * create 2020-07-09-19:44
 */
@Configuration
public class FeignConfig
{

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
//       full  详细，none 没有 ,basic 基础
    }
}
