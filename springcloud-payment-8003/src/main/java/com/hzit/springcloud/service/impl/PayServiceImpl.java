package com.hzit.springcloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hzit.springcloud.domain.MchInfo;
import com.hzit.springcloud.mapper.MchInfoMapper;
import com.hzit.springcloud.mapper.MchPayChannelMapper;
import com.hzit.springcloud.mapper.PaySerialNoMapper;
import com.hzit.springcloud.req.PayReq;
import com.hzit.springcloud.service.IPayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * author biXia
 * create 2020-07-13-22:49
 */
@Service
@Slf4j

public class PayServiceImpl implements IPayService
{
    @Resource
    private MchInfoMapper mchInfoMapper;

    @Resource
    private MchPayChannelMapper mchPayChannelMapper;

    @Resource
    private PaySerialNoMapper paySerialNoMapper;

    @Override
    public void Pay(PayReq payReq) throws Exception
    {

        //2.签名验证
        JSONObject params = (JSONObject) JSONObject.toJSON(payReq);
        MchInfo mchInfo = mchInfoMapper.selectByPrimaryKey(payReq.getMchId());

        //3.商户状态
        if (ObjectUtils.isEmpty(mchInfo) || "1".equals(mchInfo.getStatus()))
        {
            log.info("商户号:{}不存在或者状态异常,", payReq.getMchId());
        }


    }
}
