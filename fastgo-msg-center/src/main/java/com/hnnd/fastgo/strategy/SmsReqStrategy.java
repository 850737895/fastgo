package com.hnnd.fastgo.strategy;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.hnnd.fastgo.constant.AliSmsConstants;
import com.hnnd.fastgo.enumration.SmsTypeEnum;
import com.hnnd.fastgo.temp.Sms;

import java.util.Map;

/**
 * Created by Administrator on 2018/11/27.
 */
public  class SmsReqStrategy {

    //生成短信请求对象
    public SendSmsRequest generatorSmsReq(Sms sms){
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(sms.getReceiver());
        request.setSignName(AliSmsConstants.ALIYUN_SMS_SIGN);
        request.setConnectTimeout(AliSmsConstants.ALIYUN_SMS_CONN_TIMEOUT);
        request.setReadTimeout(AliSmsConstants.ALIYUN_SMS_READ_TIMEOUT);
        request.setOutId("yourOutId");
        //设置模版
        String smsTemplateId = SmsTypeEnum.getValueByCode(sms.getSmsType());
        request.setTemplateCode(smsTemplateId);
        request.setTemplateParam(JSON.toJSON(sms.getTextMap()).toString());
        return request;
    }

}
