package com.hzit.springcloud.order.shiro;

import com.hzit.springcloud.order.domain.UserInfo;
import com.hzit.springcloud.order.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * author biXia
 * create 2020-07-16-18:53
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm
{


    @Resource
    private  IUserService userService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        return null;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        log.info("执行了-----> 认证doGetAuthenticationInfo ");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        UserInfo userInfo = userService.queryByMobile(userToken.getUsername());
        if(ObjectUtils.isEmpty(userInfo)){
            return null;  //抛出异常UnknownAccountException
        }
        //密码认证

        return new SimpleAuthenticationInfo("",userInfo.getPassword(),"");
    }
}
