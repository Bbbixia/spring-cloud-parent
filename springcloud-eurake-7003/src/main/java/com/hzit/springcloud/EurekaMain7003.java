package com.hzit.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * author biXia
 * create 2020-07-13-19:29
 */
@SpringBootApplication
@EnableEurekaServer

public class EurekaMain7003
{
    public static void main(String[] args)
    {
        SpringApplication.run(EurekaMain7003.class,args);
    }

}
