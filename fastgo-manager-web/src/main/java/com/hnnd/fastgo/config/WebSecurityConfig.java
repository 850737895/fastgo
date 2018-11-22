package com.hnnd.fastgo.config;

import com.hnnd.fastgo.compent.MrgWebUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MrgWebUserDetailService mrgWebUserDetailService;

    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().
                    loginPage("/login.html").defaultSuccessUrl("/admin/index.html",true)
                                            .failureUrl("/login.html").loginProcessingUrl("/login").
                and()
                    .authorizeRequests()
                        .antMatchers("/login.html","/css/**","/img/**","/js/**","/puugins/**","/login").permitAll()
                        .antMatchers("/amdin/**").hasRole("ADMIN")
                .and()
                    .authorizeRequests().anyRequest().authenticated()
                .and().headers().frameOptions().sameOrigin()
                .and().logout().logoutUrl("/signOut")
                .and().csrf().disable();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mrgWebUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
