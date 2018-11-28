package com.hnnd.fastgo.config;

import com.hnnd.fastgo.service.ShopUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全配置
 * Created by Administrator on 2018/11/23.
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurity extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/shoplogin.html")
                        .defaultSuccessUrl("/admin/index.html")
                        .loginProcessingUrl("/login")
                        .failureUrl("/shoplogin.html")
                .and()
                        .authorizeRequests()
                        .antMatchers("/shoplogin.html","/sampling.html","/register.html","/cooperation.html","/css/**","/img/**","/js/**","/plugins/**","/login","/seller/validate/**","/seller/register","/seller/testFileUpload").permitAll()
                        .antMatchers("/amdin/**").hasRole("SELLER")
                .and()
                        .authorizeRequests().anyRequest().authenticated()
                .and()
                        .logout().logoutUrl("/signOut")
                .and()
                        .headers().frameOptions().sameOrigin()
                .and()
                        .csrf().disable();



    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(shopUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private ShopUserDetailService shopUserDetailService;
}
