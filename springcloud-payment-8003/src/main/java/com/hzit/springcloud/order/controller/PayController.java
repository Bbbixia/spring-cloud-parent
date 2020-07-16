package com.hzit.springcloud.order.controller;

import com.hzit.springcloud.req.PaymentReq;
import com.hzit.springcloud.resp.CommonResult;
import com.hzit.springcloud.resp.PayResultData;
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
 */
@RestController
@Slf4j
public class PayController
{

    @Resource
    private IPayService payService;

    /**
     * 统一的支付接口
     *
     * @param PaymentReq
     */

    @PostMapping("pay/toPay")
    public CommonResult<PayResultData> pay(@RequestBody @Valid PaymentReq PaymentReq)
    {
        //入参出参,打印
        log.info("接受到的请求参数为： {} 的支付请求", PaymentReq);
        CommonResult<PayResultData> commonResult = new CommonResult<>();
        try
        {   //8.封装javabean对象返回给对应的业务系统   TODO
            commonResult = payService.Pay(PaymentReq);
            return commonResult;

        }
        catch (Exception e)
        {
            log.error("Exception", e);
            commonResult.setMessage(e.getMessage());
            commonResult.setCode(-1);
            return commonResult;
        }

    }

}
