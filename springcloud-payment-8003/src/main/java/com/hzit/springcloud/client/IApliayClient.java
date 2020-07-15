package com.hzit.springcloud.client;

import com.hzit.springcloud.req.AliapyPayReq;
import com.hzit.springcloud.resp.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * author biXia
 * create 2020-07-14-18:33
 */
@Component
@FeignClient(value = "ALIPAY-FRONT")  // TODO: 2020/7/14 0014
public interface IApliayClient
{

    /**
     * 支付宝扫码支付,调用alipay服务
     * @param aliapyPayReq
     * @return
     */
    @RequestMapping("/alipay/preCreate")  //此处url需要与调用类一致，不然找不到
     CommonResult<String> toAlipay(@RequestBody AliapyPayReq aliapyPayReq);
}
