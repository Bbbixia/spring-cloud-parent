package com.hzit.springcloud.service;

import com.hzit.springcloud.req.PaymentReq;
import com.hzit.springcloud.resp.CommonResult;
import com.hzit.springcloud.resp.PayResultData;

/**
 * author biXia
 * create 2020-07-13-22:49
 * 支付的相关接口
 */
public interface IPayService
{

    /**
     * 处理支付请求
     * @param payReq
     */
    CommonResult<PayResultData> Pay(PaymentReq payReq) throws Exception;
}
