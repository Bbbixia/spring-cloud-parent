package com.hzit.springcloud.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * author biXia
 * create 2020-07-15-19:01
 */
@EnableFeignClients
@MapperScan("com.hzit.springcloud.order.mapper")
@EnableEurekaClient
@SpringBootApplication
public class OrederMain8002
{

    public static void main(String[] args)
    {
        SpringApplication.run(OrederMain8002.class,args);
    }


}
