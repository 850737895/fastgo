package com.hnnd.fastgo.compent;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.hnnd.fastgo.constant.AliSmsConstants;
import com.hnnd.fastgo.exception.SendSmsException;
import com.hnnd.fastgo.properties.AliYunSmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统默认的发送短信验证码（使用阿里云短信推送）
 * Created by 85073 on 2018/9/8.
 */
@Slf4j
@EnableConfigurationProperties(AliYunSmsProperties.class)
public class AliSmsCodeSender implements SmsCodeSender {

    @Autowired
    private AliYunSmsProperties aliYunSmsProperties;


    @Autowired
    private IAcsClient acsClient ;

    @PostConstruct
    public void initAcsClient() throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliYunSmsProperties.getAliYunSmsAccessKey(), aliYunSmsProperties.getAliYunSmsAccessSECET());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", AliSmsConstants.ALIYUN_SMS_PRODUCT, AliSmsConstants.ALIYUN_SMS_DOMAIN);
        acsClient = new DefaultAcsClient(profile);

    }

    @Override
    public void sender(String mobile, String code) throws SendSmsException, ClientException {
        Map<String,Object> inParam = new HashMap<>();
        inParam.put("mobile",mobile);
        inParam.put("code",code);

        SendSmsRequest sendSmsRequest = buildSendSmsRequest(inParam);
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(sendSmsRequest);
        if(!"OK".equals(sendSmsResponse.getCode())) {
            log.warn("发送短信失败..............");
            throw new SendSmsException(Integer.parseInt(sendSmsResponse.getCode()),sendSmsResponse.getMessage());
        }
    }

    /**
     * 构建SendSmsRequest
     * @param inParam
     * @return
     */
    private SendSmsRequest buildSendSmsRequest(Map<String,Object> inParam){
        SendSmsRequest request = new SendSmsRequest();

        String phoneNum = inParam.get("mobile").toString();
        inParam.remove("mobile");
        String reqParam = JSON.toJSONString(inParam);

        request.setPhoneNumbers(phoneNum);
        request.setSignName(aliYunSmsProperties.getAliYunSmsSign());
        request.setConnectTimeout(AliSmsConstants.ALIYUN_SMS_CONN_TIMEOUT);
        request.setReadTimeout(AliSmsConstants.ALIYUN_SMS_READ_TIMEOUT);
        request.setTemplateCode(aliYunSmsProperties.getAliYunBizSmsTempId());
        request.setTemplateParam(reqParam);
        request.setOutId("yourOutId");

        return request;
    }
}
