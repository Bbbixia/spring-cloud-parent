package com.hzit.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaySerialNo implements Serializable
{
    private Long id;

    private String goodsBody;

    private String goodsSubject;

    private String reqSerialNo;

    private Date timeOut;

    private BigDecimal amount;

    private String goodsType;

    private String quitUrl;

    private String mchOrderNo;

    private String status;

    private String mchId;

    private String payChannel;

    private String notifyUrl;

    private String respSerialNo;

    private String respMsg;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;


}