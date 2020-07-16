package com.hzit.springcloud.order.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * author biXia
 * create 2020-07-16-18:51
 * <p>
 * shiro的三大核心对象
 * 1.ShiroFilterFactoryBean 连接到前端
 * 2.DefaultWebSecurityManager 接管对象
 * 3.创建realm对象     创建对象
 */
@Configuration
@Slf4j
public class ShiroConfig
{


    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager defaultWebSecurityManager)
    {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
        anon： 无需认证
        authc： 必须认证了才能访问
        user：    拥有对某个资源的权限才能访问
        perms: 拥有某个资源的权限才能访问
        role： 拥有某个角色权限才能访问
         */

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/add", "authc"); //设置需要认证才能登录/user/add页面
//        filterMap.put("/user/*", "authc");

        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录的请求，若没有认证到这个页面
        bean.setLoginUrl("/show/login");
        return bean;
    }


    @Bean(name = "SecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联SecurityManager
        securityManager.setRealm(myShiroRealm);
        return securityManager;

    }

    /**
     * 创建realm对象，需要自定义
     * @return
     */
    @Bean(name = "myShiroRealm") //name属性可以不加，默认就是方法名字
    public MyShiroRealm myShiroRealm()
    {
        return new MyShiroRealm();
    }

}
