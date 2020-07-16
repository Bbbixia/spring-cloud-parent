package com.hzit.springcloud.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * author biXia
 * create 2020-07-16-20:42
 */
@Controller
@Slf4j
public class LoadingController
{
    /**
     * 加载登录页面
     */
    @GetMapping("/show/login")
    public String login()
    {
        log.info("...加载登录页面..");
        return "login";
    }


    /**
     * 加载列表页面
     */
    @GetMapping("/goods/list")
    public String goodsList()
    {
        return "goods_list";
    }

}
