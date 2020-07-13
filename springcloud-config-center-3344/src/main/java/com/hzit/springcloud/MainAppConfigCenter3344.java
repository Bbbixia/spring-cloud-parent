package com.hzit.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * author biXia
 * create 2020-07-13-20:23
 * 消息总线
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer //配置中心的注解
public class MainAppConfigCenter3344
{
    public static void main(String[] args)
    {
        SpringApplication.run(MainAppConfigCenter3344.class,args);
    }
}
