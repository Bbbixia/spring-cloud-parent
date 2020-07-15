package com.hzit.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.hzit.springcloud.req.AliapyPayReq;
import com.hzit.springcloud.resp.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * author biXia
 * create 2020-07-14-18:42
 */
@RestController
@Slf4j
public class AlipayController
{
    //TODO   这些数据应该从springCloud config读取
    //应用私钥，用来加密数据
    private String apiPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC64VIFZHn6vs8R7AWIE6VAcu9q2PooIEMjCKSQnUtgyDCBTxB/+yiPlzi+AfO8c8E6YYzMiBkKaECBHi1l/Lb/UU6bmG+si4xuI0LtRvgdbGXpN/YLBK+EHVgxcEpu7EfSwg07YaWVD1cM3ELU8fi+7Z1R1gcgmUlmzQNNAXqxLnHcNiyIwp9a0i7CkxxJgcJFr9w7Ctkt7v7kuMHQTzJXRYtDDfIRdSOjdozgkfGgQY2wyG/LxdGmzppavQkp+XcRw/Rnmphqc1Maw0PYEwFy4zfnoLxkCFTpsB5yT8re5ZYFB7sR+lmpgnQL48T9ay56GPfNAhmT1R70xQXCUSnzAgMBAAECggEAa7mK4FTlA6eOEdSt85zve1FFtZ/Rw+OlD8LhqgMh+m4rl76N0lhjLzKKoOzbJVzMm+yzjws89ATYehV9MfcVPeQOnh6m8R2YG7zzzXdFeyCLNn7ts7hFvLB1sDn8jihh1pnJqttE6cHn+h/V3o6FRvoN36iyfxYde8beCju6D/urq6PijfPZ/wYmL3LeruYsjm1vYIgLndm5eThbRsJ6VlTvywAJxpJ25Jco46yKH4GJV9AGMdJqMzADZ4RVgCTC5dUl/HM8j36/GJ+Jm8JrPpibv4Es375OAfsB5FVvv27djo5SmiL/YtcSkaA0TwgA6YIcj1QqL53Grey8neM5UQKBgQDe+t2QSGAwIo39QP+N7G9nYdZsGfATBWiS9FdLeI4DaeHUwraKvAajPrtbiBs+xrglq8e/z1nwodD4KjClN06sX+FSEytIrTNQqc1QvCM/9JbB3gdzhpS8zU6DWmE82rlm0nVfiYalqKa84Jjm7lpS8jm1city2FibcM1rRifMzQKBgQDWjerwdN86w6gVVR2oq9XKfmgAeydZoLBwqZvbsnO4pV/gaD7eIkuJ7XO+31h6JO8BodBGzaV3BndxUjqh9mtl4njUAPfPHh318yUuBzbLCo7ojvPiCIO5SU1YXRi+h5ctcIlE7/G/e9V1RTa96qkmxBgV/I9TbKZPCnTJnhzRvwKBgDtuqjeW0uyBiU8DUBpH9rmbfkd58l9fhawFrG7VRyTpx+OpsnXv/+TrB40yDfEBjnub+bXKUrKsrsI2nJis0S9oruntahAI+jB50du/CbY2PyiVQW8tY9+e6VphcKGDuv8JAXfhdB4bgbW+N4L17OVG4tLWB3ao65Uiz93T6S/RAoGBAM2UtmUo1yyrhUbLg9u2queYL3cmihHrUqThH6JnX1Kxwyh3OqYGHnNOvvVJdLG06f6aWcM+ESn3XEONEvlV8JpzYmthchFXPkYIcLul1e6/cyeKsk0G85B6mE3vBmKp8UVs61rOvslHksUHSOi5DNNEWf/XPE4ncUOTNUxq4BppAoGBAKC3Zf6w7iUPn/KvHqGAG/EfYlIyvHUv0e/+Ed7zGZ/js3DPNwOHA9fvpQFGjG2GoYClufloaRLzkE1DNxuGMXEFEMPDeB8wv785ECP0oJPwHO/Tb45180PQZmhnhlc+py7qZiwNiDzPiEa4vna4jeuwdxGoy3plM6/vZ55bwCEJ";

    //支付宝的公钥，用来验证支付宝返回的数据
    private String alipayPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnzJnbTT7sBQJTZvp3gGztei1V2eONrrbhxuPHojkAFTQzGE7nsWL2/TvVbOJihCq8JQtU9gSXBedNePetNLz4R7eMcZztTV9M9kxxwB5TKxjbI3l9DFDj3Q9sOUq8F1Afy8XiBfYdqvv+Haz4AWDdo6EljvXY6amrXbyBralIyXC/7exOqLs17/gx4DInfdf8ophOFbRSYbQCcRbDyxPdqT7mUY9ozfmoWaj/acjbH2gGGY26ptF9bDtkrPYLPgeIUNqYU1LsWgqqDxhL5eDYIGGvPMr5aFq9s29BjEAWdoDDAnUt8R0azhc1A6I1ONVspQToxPAMMVaYAirb1EXrQIDAQAB";

    private String appId = "2016101200667654";

    private String url = "https://openapi.alipaydev.com/gateway.do";


    /**
     * 支付接口  支付服务调用aliapy(扫码服务)的第三方通过这个接口
     */
    @PostMapping("/alipay/preCreate")
    public CommonResult<String> toPay(@RequestBody AliapyPayReq aliapyPayReq)
    {
        log.info("接受到支付请求,请求参数为：{}", aliapyPayReq); //入参打印

        //alipay.trade.precreate(统一收单线下交易预创建)
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(url, appId, apiPrivateKey, "json", "utf-8", alipayPubKey, "RSA2");
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();


        JSONObject jsonObject = new JSONObject();
        //必传参数
        jsonObject.put("out_trade_no", aliapyPayReq.getOutTradeNo());    //商户订单号
        jsonObject.put("total_amount", aliapyPayReq.getAmount());//订单总金额，单位为元，
        jsonObject.put("subject", aliapyPayReq.getSubject());        //订单标题
        jsonObject.put("body", aliapyPayReq.getBody());         //对交易或商品的描述
        request.setBizContent(jsonObject.toJSONString()); //json字符串


        CommonResult result = new CommonResult();   //返回结果封装

        AlipayTradePrecreateResponse response = null;
        try
        {
            response = alipayClient.execute(request);

            if ("10000".equals(response.getCode()))
            {
                result.setCode(0);
                result.setMessage("调用成功");
                result.setData(response.getQrCode());
                log.info("调用成功 ：");
            }
            else
            {
                result.setMessage(response.getMsg());
                result.setCode(-1);
                log.error("调用失败：");
            }
        }
        catch (AlipayApiException e)
        {
            log.error("AlipayApiException:{}", aliapyPayReq.getOutTradeNo(), e); //打印订单号和错误信息
            result.setCode(-1);
            result.setMessage(e.getErrMsg());
        }
        log.info("支付处理完成,返回参数为：{}", result);
        return result;

    }


}
