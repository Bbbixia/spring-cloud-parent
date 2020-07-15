package com.hzit.springcloud.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 订单系统请求支付宝服务的参数
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AliapyPayReq implements Serializable
{

    private String outTradeNo;
    private String amount;
    private String subject;
    private String body;

}
