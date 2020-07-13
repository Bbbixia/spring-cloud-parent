package com.hzit.springcloud.mapper;

import com.hzit.springcloud.domain.MchPayChannel;

/**
 * author biXia
 * create 2020-07-13-22:10
 */
public interface MchPayChannelMapper
{

    int deleteByPrimaryKey(Integer id);

    int insert(MchPayChannel record);

    int insertSelective(MchPayChannel record);

    MchPayChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MchPayChannel record);

    int updateByPrimaryKey(MchPayChannel record);

}
