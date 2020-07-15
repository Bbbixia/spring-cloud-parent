package com.hzit.springcloud.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 支付服务返回详细数据给订单服务
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PayResultData implements Serializable
{

    private String mchOrderNo; //商户订单号

    private String reqSerialNo; //支付系统请求流水

    private String channelId; //支付方式

    private String url; //二维码地址,或者其他


    private String sign; //签名串




}
