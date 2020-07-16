package com.hzit.springcloud.order.controller;

import com.hzit.springcloud.order.constant.RedisKeyContant;
import com.hzit.springcloud.order.domain.UserInfo;
import com.hzit.springcloud.order.req.UserReq;
import com.hzit.springcloud.order.resp.UserData;
import com.hzit.springcloud.order.service.IUserService;
import com.hzit.springcloud.resp.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * author biXia
 * create 2020-07-15-19:50
 * 登录接口
 */
@RestController
@Slf4j
public class LoginController
{
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private IUserService userService;



    @PostMapping("/user/login")
    public CommonResult login(@RequestParam("mobile")String mobile,@RequestParam("password")String password   )
    {
        log.info("接收到手机号:{},的登录请求:{}", mobile,password);

        CommonResult result = new CommonResult();

        UserInfo userInfo = userService.queryByMobile(mobile);
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //生成token
        UsernamePasswordToken token = new UsernamePasswordToken(mobile, password);
        //设置记住我
        token.setRememberMe(true);
        try
        {
            //验证token
            subject.login(token);  //执行登录请求，没有异常就是成功了
            log.info("=========logger.info ============:{}", subject.isAuthenticated());

            if (subject.isAuthenticated() == true)
            {
                //创建一个hash类型 放入缓存
                HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
                String userId = userInfo.getId().toString();
                String redisKey = RedisKeyContant.USER_INFO_KEY + ":" + userInfo.getId();
                hashOperations.put(redisKey, userId, userInfo);
                redisTemplate.expire(redisKey, 2, TimeUnit.HOURS);

                //前端需要的返回值data数据
                UserData data = new UserData();
                data.setUserId(userInfo.getId().toString());
                data.setUserName(userInfo.getUserName().toString());

                result.setData(data);
                result.setCode(0);
                result.setMessage("成功");
                return result;
            }
            else
            {
                result.setCode(0);
                result.setMessage("用户名或密码错误");
                return result;
            }
        }
        catch (UnknownAccountException e)
        {
            log.error("UnknownAccountException", e);
            result.setCode(-1);
            result.setMessage("登录失败");
            return result;
        }
        catch (IncorrectCredentialsException e)
        {
            log.error("IncorrectCredentialsException", e);
            result.setCode(-1);
            result.setMessage("登录失败");
            return result;
        }

    }

}
