package com.hzit.springcloud.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SecKillGoods implements Serializable
{
    private Long id;

    private Long goodsId;

    private BigDecimal seckilPrice;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;


}