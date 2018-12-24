package com.hnnd.fastgo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * cas客户端配置
 * Created by 85073 on 2018/12/22.
 */
@Data
@ConfigurationProperties(prefix = "security.cas.client")
public class CasClientProperties {

    private String host;

    private String login;

    private String logout;

}
