package com.hnnd.fastgo.compent;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.hnnd.fastgo.constant.AliSmsConstants;
import com.hnnd.fastgo.exception.SendSmsException;
import com.hnnd.fastgo.strategy.SmsReqStrategy;
import com.hnnd.fastgo.temp.Sms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 系统默认的发送短信验证码（使用阿里云短信推送）
 * Created by 85073 on 2018/9/8.
 */
@Slf4j
@Component
public class AliSmsCodeSender implements SmsCodeSender {


    private IAcsClient acsClient;

    @PostConstruct
    public void initAcsClient() throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", AliSmsConstants.ALIYUN_SMS_ACCESSKEY, AliSmsConstants.ALIYUN_SMS_SECET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", AliSmsConstants.ALIYUN_SMS_PRODUCT, AliSmsConstants.ALIYUN_SMS_DOMAIN);
        acsClient = new DefaultAcsClient(profile);

    }

    @Override
    public void sender(Sms sms) throws SendSmsException, ClientException {

        SendSmsRequest sendSmsRequest = buildSendSmsRequest(sms);
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(sendSmsRequest);
        if (!"OK".equals(sendSmsResponse.getCode())) {
            log.warn("发送短信失败..............");
            throw new SendSmsException(Integer.parseInt(sendSmsResponse.getCode()), sendSmsResponse.getMessage());
        }
    }

    /**
     * 构建对象
     *
     * @param sms
     * @return
     */
    private SendSmsRequest buildSendSmsRequest(Sms sms) {
        SmsReqStrategy smsReqStrategy = new SmsReqStrategy();
        return smsReqStrategy.generatorSmsReq(sms);
    }
}
