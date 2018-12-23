package com.hnnd.fastgo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * cas认证服务端配置属性
 * Created by 85073 on 2018/12/22.
 */
@Data
@ConfigurationProperties(prefix = "security.cas.server")
public class CasServerProperties {

    private String host;

    private String login;

    private String logout;

    private String redirectUrl;
}
