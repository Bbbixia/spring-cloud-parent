package com.hzit.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * author biXia
 * create 2020-07-08-10:48
 * 前端通用的数据格式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    /**
     * 提供没有data类型的构造函数
     * param code
     * param message
     */
    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }
}
