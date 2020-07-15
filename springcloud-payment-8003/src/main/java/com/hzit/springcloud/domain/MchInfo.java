package com.hzit.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * author biXia
 * create 2020-07-13-22:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MchInfo implements Serializable
{
    private String mchId;

    private String mchName;

    private String status;

    private String reqKey;

    private String respKey;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

}
