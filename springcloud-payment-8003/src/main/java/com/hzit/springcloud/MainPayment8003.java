package com.hzit.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * author biXia
 * create 2020-07-13-20:28
 */
@MapperScan("com.hzit.springcloud.mapper")
@EnableEurekaClient
@SpringBootApplication
public class MainPayment8003
{
    public static void main(String[] args)
    {
        SpringApplication.run(MainPayment8003.class,args);
    }

}
