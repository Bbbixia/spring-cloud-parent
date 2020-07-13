package com.hzit.springcloud.controller;

import com.hzit.springcloud.req.PayReq;
import com.hzit.springcloud.service.IPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * author biXia
 * create 2020-07-13-22:48
 *
 */
@RestController
@Slf4j
public class PayController
{

    @Resource
    private IPayService payService;

    @PostMapping("pay/toPay")
    public void pay(@RequestBody @Valid PayReq payReq )
    {
        //入参出参,打印
        log.info("接受到的请求参数为");
        try
        {

            payService.Pay(payReq);
            //8.封装javabean对象返回给对应的业务系统   TODO
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
