package com.hzit.springcloud.service;

import com.hzit.springcloud.req.PayReq;

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
     void Pay(PayReq payReq) throws Exception;
}
