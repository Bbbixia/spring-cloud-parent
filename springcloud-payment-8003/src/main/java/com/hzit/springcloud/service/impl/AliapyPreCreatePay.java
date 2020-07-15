package com.hzit.springcloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hzit.springcloud.client.IApliayClient;
import com.hzit.springcloud.domain.MchInfo;
import com.hzit.springcloud.enums.PayEnum;
import com.hzit.springcloud.factory.PayStrategyFactory;
import com.hzit.springcloud.mapper.MchInfoMapper;
import com.hzit.springcloud.req.AliapyPayReq;
import com.hzit.springcloud.req.PaymentReq;
import com.hzit.springcloud.resp.CommonResult;
import com.hzit.springcloud.resp.PayResultData;
import com.hzit.springcloud.service.IPayStrategyService;
import com.hzit.springcloud.utils.PayDigestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * author biXia
 * create 2020-07-14-17:13
 * 支付宝手机扫码支付的工厂方法
 */
@Service
@Slf4j
public class AliapyPreCreatePay implements IPayStrategyService, InitializingBean
{

    @Resource
    private IApliayClient client;

    @Resource
    private MchInfoMapper mchInfoMapper;

//    @Resource
//    private PayStrategyFactory payStrategyFactory;

    /**
     * 将ALIPAY_TRADE_PRECREATE 这个实例放入到map中 ,
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception
    {
//        payStrategyFactory.payMap.put(PayEnum.ALIPAY_TRADE_PRECREATE.getCode(), this);
        //初始化的时候，把支付宝这个实例放入map中
        PayStrategyFactory.payMap.put(PayEnum.ALIPAY_TRADE_PRECREATE.getCode(),this);
    }


    @Override
    public CommonResult<?> payStrategy(PaymentReq PaymentReq, String reqSerialNo)
    {
        CommonResult commonResult = new CommonResult();

        //调用支付宝扫码支付的接口
        AliapyPayReq aliapyPayReq = new AliapyPayReq();
        aliapyPayReq.setAmount(PaymentReq.getAmount());
        aliapyPayReq.setBody(PaymentReq.getBody());
        aliapyPayReq.setOutTradeNo(PaymentReq.getMchOrderNo());
        aliapyPayReq.setSubject(PaymentReq.getSubject());

        CommonResult<String> result = client.toAlipay(aliapyPayReq);

        log.info("调用支付宝扫码支付接口返回：{}", result);  //获取并打印支付宝返回的结果

        if (0 != result.getCode())  //返回的结果失败
        {
            commonResult.setCode(-1);
            commonResult.setMessage(result.getMessage());
            return commonResult;
        }
        //   ==0 则为成功

        commonResult.setCode(0);
        commonResult.setMessage("调用成功");


        PayResultData data = new PayResultData();  //封装返回的参数

        data.setUrl(result.getData());
        data.setChannelId(PaymentReq.getChannelId());
        data.setMchOrderNo(PaymentReq.getMchOrderNo());
        data.setReqSerialNo(PaymentReq.getMchOrderNo());


        JSONObject json = (JSONObject) JSONObject.toJSON(data);
        // 使用MD5生成返回签名
        MchInfo mchInfo = mchInfoMapper.selectByPrimaryKey(PaymentReq.getMchId());

        String sign = PayDigestUtil.getSign(json, mchInfo.getMchId());

        data.setSign(sign);

        commonResult.setData(data);
        log.info("commonResult出参 " +commonResult.toString());
        return commonResult;
    }


}
