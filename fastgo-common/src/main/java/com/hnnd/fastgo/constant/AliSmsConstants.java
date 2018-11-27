package com.hnnd.fastgo.constant;

/**
 * 阿里云短信配置
 * Created by 85073 on 2018/11/26.
 */
public class AliSmsConstants {

    /**阿里云短信通知服务商*/
    public static final String ALIYUN_SMS_PRODUCT = "Dysmsapi";

    /**阿里云短信通知服务商域名*/
    public static final String ALIYUN_SMS_DOMAIN = "dysmsapi.aliyuncs.com";
    /**短信签名*/
    public static final String ALIYUN_SMS_SIGN = "长沙人才一体化平台";

    /**读取超时时间5S*/
    public static final Integer ALIYUN_SMS_READ_TIMEOUT = 5000;
    /**连接超时时间5S*/
    public static final Integer ALIYUN_SMS_CONN_TIMEOUT = 5000;

    /**商家账号审核通过短信模板*/
    public static final String ALIYUN_ADUIT_PASS_TEMPID = "SMS_151830137";

    /**商家账号审核通过短信模板*/
    public static final String ALIYUN_ADUIT_UNPASS_TEMPID = "SMS_151830322";
    /**阿里云短信平台访问的key*/
    public static final String ALIYUN_SMS_ACCESSKEY= "LTAIeWbHa0UzAANA";
    /**阿里云短信平台访问的密码*/
    public static final String ALIYUN_SMS_SECET= "HjAh0WFWc1x59K4wAYSWKHXR4w38zg";
/*    *//**商家账户审核通过*//*
    public static final String BIZ_SELLER_ADUIT_PASS="PASS";

    *//**商家账户审核不通过*//*
    public static final String BIZ_SELLER_ADUIT_UNPASS="UNPASS";

    *//**商家账户审核不通过*//*
    public static final String BIZ_SELLER_ACCT_CLODE="CLOSE";*/
}
