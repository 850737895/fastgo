package com.hnnd.fastgo.compent;


import com.aliyuncs.exceptions.ClientException;
import com.hnnd.fastgo.exception.SendSmsException;

/**
 * 短信验证码发送的短信
 * Created by 85073 on 2018/9/8.
 */
public interface SmsCodeSender {

    /**
     * 发送短信的类容
     * @param mobile 手机号码
     * @param content  短信验证码
     */
    void sender(String mobile, String content) throws SendSmsException, ClientException;
}
