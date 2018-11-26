package com.hnnd.fastgo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云短信配置
 * Created by Administrator on 2018/9/11.
 */
@Data
@ConfigurationProperties(prefix = "aliYunSms")
public class AliYunSmsProperties {
    /**阿里云签名配置*/
    private String aliYunSmsSign;
    /**ACCESS_KEY*/
    private String aliYunSmsAccessKey;
    /*密碼*/
    private String aliYunSmsAccessSECET;

    private String aliYunBizSmsTempId;
}
