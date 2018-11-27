package com.hnnd.fastgo.strategy;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.hnnd.fastgo.temp.SmsContext;

import java.util.Map;

/**
 * 使用阿里云短信请求对象的策略抽象类
 * Created by Administrator on 2018/11/27.
 */
public interface SmsReqStrategy {

    //生成短信请求对象
    SendSmsRequest generatorSmsReq(SmsContext smsContext);
}
