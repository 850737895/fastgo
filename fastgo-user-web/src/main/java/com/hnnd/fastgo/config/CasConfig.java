package com.hnnd.fastgo.config;

import com.hnnd.fastgo.properties.CasClientProperties;
import com.hnnd.fastgo.properties.CasServerProperties;
import com.hnnd.fastgo.service.impl.CasUserDetailServiceImpl;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

/**
 * cas 配置
 * Created by 85073 on 2018/12/22.
 */
@Configuration
@EnableConfigurationProperties({CasClientProperties.class, CasServerProperties.class})
public class CasConfig {

    @Autowired
    private CasServerProperties casServerProperties;

    @Autowired
    private CasClientProperties casClientProperties;

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(casClientProperties.getHost()+casClientProperties.getLogin());
        serviceProperties.setAuthenticateAllArtifacts(true);
        return serviceProperties;
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter(AuthenticationManager authenticationManager,CasClientProperties casClientProperties) {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        casAuthenticationFilter.setAuthenticationManager(authenticationManager);
        //casAuthenticationFilter.setServiceProperties(serviceProperties());
        casAuthenticationFilter.setFilterProcessesUrl(casClientProperties.getLogin());

        return casAuthenticationFilter;
    }

    /**
     * 票据验证器
     * @return
     */
    @Bean
    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
        return new Cas20ServiceTicketValidator(casServerProperties.getHost());
    }

    /**
     * cas认证入口点配置
     * @return casAuthenticationEntryPoint
     */
    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint(ServiceProperties serviceProperties) {
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
        casAuthenticationEntryPoint.setLoginUrl(casServerProperties.getLogin());
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties);
        return casAuthenticationEntryPoint;
    }


    @Bean
    public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> casUserDetailService() {
        return new CasUserDetailServiceImpl();
    }



    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {

        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setKey("casAuthenticationProviderKey");
        provider.setServiceProperties(serviceProperties());
        provider.setTicketValidator(cas20ServiceTicketValidator());
        provider.setAuthenticationUserDetailsService(casUserDetailService());

        return provider;
    }




    /*单点登出过滤器*/
    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(casServerProperties.getHost());
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }


    /**
     * 退出过滤器
     * @return
     */
    @Bean
    public LogoutFilter logoutFilter() {
        String logoutRedirectPath = casServerProperties.getLogout()+"?service="+casServerProperties.getRedirectUrl();
        LogoutFilter logoutFilter = new LogoutFilter(logoutRedirectPath, new SecurityContextLogoutHandler());
        //设置客户端退出登录的url
        logoutFilter.setFilterProcessesUrl(casClientProperties.getLogout());
        return logoutFilter;
    }



}
