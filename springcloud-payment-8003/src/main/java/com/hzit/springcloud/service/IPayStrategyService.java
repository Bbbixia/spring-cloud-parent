package com.hzit.springcloud.service;

import com.hzit.springcloud.req.PaymentReq;
import com.hzit.springcloud.resp.CommonResult;

/**
 * 支付策略接口
 */
public interface IPayStrategyService
{

    /**
     * 支付接口
     * @return
     */
     CommonResult <?> payStrategy(PaymentReq PaymentReq, String reqSerialNo);  //此处的参数 reqSerialNo 没什么用感觉
}
