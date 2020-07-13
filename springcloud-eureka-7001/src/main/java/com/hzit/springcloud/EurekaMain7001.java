package com.hzit.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * author biXia
 * create 2020-07-13-19:00
 */
@SpringBootApplication
@EnableEurekaServer
//@EnableDiscoveryClient
public class EurekaMain7001
{
    public static void main(String[] args)
    {
        SpringApplication.run(EurekaMain7001.class,args);
    }
}
