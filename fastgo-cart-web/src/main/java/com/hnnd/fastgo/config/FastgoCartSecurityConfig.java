package com.hnnd.fastgo.config;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * 安全配置
 * Created by Administrator on 2018/12/24.
 */
@Configuration
public class FastgoCartSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CasAuthenticationEntryPoint casAuthenticationEntryPoint;

    @Autowired
    private CasAuthenticationFilter casAuthenticationFilter;

    @Autowired
    private LogoutFilter logoutFilter;

    @Autowired
    private SingleSignOutFilter singleSignOutFilter;

    @Autowired
    private CasAuthenticationProvider casAuthenticationProvider;

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**","/img/**","/js/**","/plugins/**").permitAll()
                .and()
                .authorizeRequests().anyRequest().hasRole("USER")
                .and().csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint)
                .and()
                .addFilter(casAuthenticationFilter)
                .addFilterBefore(logoutFilter, LogoutFilter.class)
                .addFilterBefore(singleSignOutFilter, CasAuthenticationFilter.class);

    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(casAuthenticationProvider);
    }
}
