package com.hnnd.fastgo.strategy;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.hnnd.fastgo.constant.AliSmsConstants;
import com.hnnd.fastgo.temp.SmsContext;

import java.util.Map;

/**
 * Created by Administrator on 2018/11/27.
 */
public abstract class AbstractSmsReqStrategy implements SmsReqStrategy {

    //生成短信请求对象
    public SendSmsRequest generatorSmsReq(SmsContext smsContext){
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(smsContext.getReciver());
        request.setSignName(AliSmsConstants.ALIYUN_SMS_SIGN);
        request.setConnectTimeout(AliSmsConstants.ALIYUN_SMS_CONN_TIMEOUT);
        request.setReadTimeout(AliSmsConstants.ALIYUN_SMS_READ_TIMEOUT);
        request.setOutId("yourOutId");
        supplementExtraProp(request,smsContext);
        return request;
    }

    /**
     * 补充发送内容
     * @param request
     * @return
     */
    public abstract void supplementExtraProp( SendSmsRequest request,SmsContext smsContext);
}
