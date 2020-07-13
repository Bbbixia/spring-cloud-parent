package com.hzit.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * author biXia
 * create 2020-07-13-22:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MchPayChannel implements Serializable
{

    private Integer id;

    private String mchId;

    private String status;

    private String bank;

    private String payChannel;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

}
