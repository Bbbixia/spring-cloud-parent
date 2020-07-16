package com.hzit.springcloud.order.mapper;

import com.hzit.springcloud.order.domain.UserInfo;
import com.hzit.springcloud.order.req.UserReq;
import org.springframework.data.repository.query.Param;

/**
 * author biXia
 * create 2020-07-15-21:55
 */
public interface UserInfoMapper
{

    /**
     *  根据手机号查询 用户信息serInfo
     * @param mobile
     * @return UserInfo
     */
    UserInfo selectByPhone(@Param("mobile") String mobile);

}
