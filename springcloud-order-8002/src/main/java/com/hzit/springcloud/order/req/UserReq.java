package com.hzit.springcloud.order.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * author biXia
 * create 2020-07-15-21:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserReq implements Serializable
{

    private String password;
    private String phone;

}
