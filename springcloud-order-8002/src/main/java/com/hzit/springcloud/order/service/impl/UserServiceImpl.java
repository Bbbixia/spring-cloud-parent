package com.hzit.springcloud.order.service.impl;

import com.hzit.springcloud.order.domain.UserInfo;
import com.hzit.springcloud.order.mapper.UserInfoMapper;
import com.hzit.springcloud.order.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author biXia
 * create 2020-07-15-19:53
 */
@Service
public class UserServiceImpl implements IUserService
{

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo queryByMobile(String mobile)
    {


        return userInfoMapper.selectByPhone(mobile);
    }

    @Override
    public UserInfo queryByUserId(String userId)
    {
        return null;
    }
}
