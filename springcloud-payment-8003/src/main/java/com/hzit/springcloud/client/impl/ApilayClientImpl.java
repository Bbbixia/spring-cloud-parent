package com.hzit.springcloud.client.impl;



import com.hzit.springcloud.client.IApliayClient;
import com.hzit.springcloud.req.AliapyPayReq;
import com.hzit.springcloud.resp.CommonResult;


/**
 * author biXia
 * create 2020-07-14-18:30
 */
public class ApilayClientImpl implements IApliayClient
{

    /**
     * fegin容错
     * @param aliapyPayReq
     * @return
     */

    @Override
    public CommonResult<String> toAlipay(AliapyPayReq aliapyPayReq)
    {
        CommonResult result = new CommonResult();
        result.setCode(-1);
        result.setMessage("调用alipay 服务异常");
        return result;
    }
}
