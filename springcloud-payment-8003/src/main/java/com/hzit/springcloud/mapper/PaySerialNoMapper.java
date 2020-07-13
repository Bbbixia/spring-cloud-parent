package com.hzit.springcloud.mapper;

import com.hzit.springcloud.domain.PaySerialNo;

/**
 * author biXia
 * create 2020-07-13-22:09
 */
public interface PaySerialNoMapper
{

    int deleteByPrimaryKey(Long id);

    int insert(PaySerialNo record);

    int insertSelective(PaySerialNo record);

    PaySerialNo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaySerialNo record);

    int updateByPrimaryKey(PaySerialNo record);
}
