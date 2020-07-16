package com.hzit.springcloud.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class seckillOrder implements Serializable
{
    private Long id;

    private Long userId;

    private Long orderId;

    private Long goodsId;

}