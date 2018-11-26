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
}
