package com.hzit.springcloud.mapper;

import com.hzit.springcloud.domain.MchInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * author biXia
 * create 2020-07-13-22:08
 */

public interface MchInfoMapper
{
    int deleteByPrimaryKey(String mchId);

    int insert(MchInfo record);

    int insertSelective(MchInfo record);

    MchInfo selectByPrimaryKey(String mchId);

    int updateByPrimaryKeySelective(MchInfo record);

    int updateByPrimaryKey(MchInfo record);

}
