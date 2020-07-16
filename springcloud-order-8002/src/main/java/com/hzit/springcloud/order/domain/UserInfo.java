package com.hzit.springcloud.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfo implements Serializable
{
    private Integer id;

    private String userName;

    private String phone;

    private String password;

    private String salt;

    private String head;

    private Integer loginCount;

    private Date registerDate;

    private Date lastLoginDate;

}