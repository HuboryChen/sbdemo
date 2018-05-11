package com.xtlh.sbdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @作者 陈坤
 * @创建日期 2018/5/11
 * @功能描述 SpringSecurity的配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Bean
    UserDetailsService myUserDetailService(){return new MyUserDEtailService();}     //注册UserDetailService的bean

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{auth.userDetailsService(myUserDetailService());}   //userDetailService验证

    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .anyRequest().authenticated()       //任何请求，登录后可以访问
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("login?error")
                .permitAll()    //登录页面用户任意访问
                .and()
                .logout().permitAll();  //注销行为任意访问
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

}
