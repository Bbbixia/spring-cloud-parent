package com.hzit.springcloud.order.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * author biXia
 * create 2020-07-15-20:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserData implements Serializable
{

    private  String userName;
    private String  userId;

}
