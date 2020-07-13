package com.hzit.springcloud.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * author biXia
 * create 2020-07-13-22:44
 * 支付请求 数据的封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PayReq implements Serializable
{
    @NotNull(message = "mchId不能为空")
    private String mchId;

    @NotNull(message = "mchOrderNo不能为空")
    private String mchOrderNo;

    @NotNull(message = "channelId不能为空")
    private String channelId;

    @NotNull(message = "currency不能为空")
    private String currency;

    @NotNull(message = "amount不能为空")
    private String amount;

    @NotNull(message = "notifyUrl不能为空")
    private String notifyUrl;

    @NotNull(message = "subject不能为空")
    private String subject;

    @NotNull(message = "body不能为空")
    private String body;

    @NotNull(message = "sign不能为空")
    private String sign; //签名串



    private String clientIp;
    private String device;

    private String param1;
    private String param2;

}
