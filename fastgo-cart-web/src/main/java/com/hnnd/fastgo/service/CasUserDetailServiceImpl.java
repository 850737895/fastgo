package com.hnnd.fastgo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 用户认证类
 * Created by 85073 on 2018/12/22.
 */
@Slf4j
public class CasUserDetailServiceImpl implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken casAssertionAuthenticationToken) throws UsernameNotFoundException {
        log.info("casAssertionAuthenticationToken:{}",casAssertionAuthenticationToken);
        String userName = casAssertionAuthenticationToken.getName();
        // 这里应该查询数据库获取具体的用户信息和权限信息
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(userName, "", authorities);
    }
}
