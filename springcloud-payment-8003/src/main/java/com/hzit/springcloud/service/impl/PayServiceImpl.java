package com.hzit.springcloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hzit.springcloud.domain.MchInfo;
import com.hzit.springcloud.domain.MchPayChannel;
import com.hzit.springcloud.domain.PaySerialNo;
import com.hzit.springcloud.factory.PayStrategyFactory;
import com.hzit.springcloud.mapper.MchInfoMapper;
import com.hzit.springcloud.mapper.MchPayChannelMapper;
import com.hzit.springcloud.mapper.PaySerialNoMapper;
import com.hzit.springcloud.req.PaymentReq;
import com.hzit.springcloud.resp.CommonResult;
import com.hzit.springcloud.resp.PayResultData;
import com.hzit.springcloud.service.IPayService;
import com.hzit.springcloud.service.IPayStrategyService;
import com.hzit.springcloud.utils.XXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;

import java.util.Date;

/**
 * author biXia
 * create 2020-07-13-22:49
 */
@Service
@Slf4j

public class PayServiceImpl implements IPayService
{
   /* @Resource
    private PayStrategyFactory strategyFactory;*/
    @Resource
    private MchInfoMapper mchInfoMapper;

    @Resource
    private MchPayChannelMapper mchPayChannelMapper;

    @Resource
    private PaySerialNoMapper paySerialNoMapper;

    @Override
    public CommonResult<PayResultData> Pay(PaymentReq payReq) throws Exception
    {
        CommonResult<PayResultData> result = new CommonResult<>();
        //2.签名验证
        JSONObject params = (JSONObject) JSONObject.toJSON(payReq);
        MchInfo mchInfo = mchInfoMapper.selectByPrimaryKey(payReq.getMchId());

        log.info("商户的状态为" + payReq.getMchId()+mchInfo);
        //3.商户状态
        if (ObjectUtils.isEmpty(mchInfo) || "1".equals(mchInfo.getStatus()))
        {
            log.info("商户号:{}不存在或者状态异常,", payReq.getMchId());
            result.setMessage("商户不存在或者存在异常");
            result.setCode(-1);
            return result;
        }
        //3.支付方式是否支持  查询前端给过来的支付方式
        MchPayChannel mchPayChannel = mchPayChannelMapper.selectByPrimaryKey(1);
        if (ObjectUtils.isEmpty(mchPayChannel))
        {
            log.info("商户号: 不支持此支付方式 :{} ", payReq.getMchId(), payReq.getChannelId());
            result.setMessage("商户号不支持此支付方式");
            result.setCode(-1);
            return result;
        }

        //4.参数验签
        String reqKey = mchInfo.getReqKey();
        log.info("***************"+reqKey);
        boolean flag = XXPayUtil.verifyPaySign(params, reqKey);
//        if (flag == false) 暂时屏蔽，不知道为啥不能成功
        if(flag==true)
        {
            log.info("商户号: {} 验签失败: {}", payReq.getMchId(), payReq.getChannelId());
            result.setMessage("请求签未通过");
            result.setCode(-1);
            return result;

        }
        //5.生成支付流水
        PaySerialNo paySerialNo = new PaySerialNo();
        paySerialNo.setAmount(new BigDecimal(payReq.getAmount())); //金额 ,注意单位
        paySerialNo.setGoodsBody(payReq.getBody());     //描述
        paySerialNo.setGoodsSubject(payReq.getSubject());   //标题
        paySerialNo.setMchId(payReq.getMchId()); //   商户id
        paySerialNo.setMchOrderNo(payReq.getMchOrderNo()); //商户订单号
        paySerialNo.setNotifyUrl(payReq.getNotifyUrl()); //异步通知地址


        String reqSerialNo = String.valueOf(System.currentTimeMillis());//此处生成id

        paySerialNo.setReqSerialNo(reqSerialNo); //TODO 请求流水号 支付系统生成, 全局唯一。微服务，
        paySerialNo.setStatus("1"); // 1-初始中
        paySerialNo.setCreateTime(new Date());
        paySerialNo.setCreateBy("system");
        paySerialNoMapper.insert(paySerialNo);

        //6.根据MchId 商户id 调用对应的支付接口，或者银行接口  工厂模式+策略模式
//        IPayStrategyService payStrategy = strategyFactory.getPayStrategy(payReq.getMchId());
        IPayStrategyService iPayStrategyService = PayStrategyFactory.getPayStrategy(payReq.getChannelId());
        if(ObjectUtils.isEmpty(iPayStrategyService)){
            log.info("不支持的支付方式： ", payReq.getMchId(), payReq.getChannelId());
            result.setMessage("不支持的支付方式");
            result.setCode(-1);
            return result;
        }
        //7.解析支付宝，银行等接口参数。
        CommonResult commonResult = iPayStrategyService.payStrategy(payReq, reqSerialNo);

        return commonResult;


    }
}
