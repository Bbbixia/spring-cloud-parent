package com.hzit.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * author biXia
 * create 2020-07-14-19:26
 * 此服务内用于处理与支付宝相关的所有第三方接口
 */
@SpringBootApplication
@EnableEurekaClient
public class AliapyPayMain8006
{
    public static void main(String[] args)
    {
        SpringApplication.run(AliapyPayMain8006.class,args);
    }

}
