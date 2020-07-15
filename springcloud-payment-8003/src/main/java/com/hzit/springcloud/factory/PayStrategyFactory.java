package com.hzit.springcloud.factory;

import com.hzit.springcloud.service.IPayStrategyService;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author biXia
 * create 2020-07-14-16:08
 */
@Component
public class PayStrategyFactory
{
    //创建一个容器
    public static Map<String, IPayStrategyService> payMap = new ConcurrentHashMap<>(); //线程安全


    /**
     * 根据code获取策略
     * @param code
     * @return
     */
    public static IPayStrategyService getPayStrategy(String code)
    {
        return payMap.get(code);
    }

}
