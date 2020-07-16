package com.hzit.springcloud.order.service;

import com.hzit.springcloud.order.domain.UserInfo;
import com.hzit.springcloud.order.req.UserReq;

/**
 * author biXia
 * create 2020-07-15-19:52
 */
public interface IUserService
{

    /**
     * 根据手机号查询
     * @param mobile
     * @return UserInfo
     */
    UserInfo queryByMobile( String mobile);


    /**
     * 根据用户ID查询
     * @param userId
     * @return UserInfo
     */
    UserInfo queryByUserId(String userId);


}
