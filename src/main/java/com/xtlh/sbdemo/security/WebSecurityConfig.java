package com.xtlh.sbdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @作者 陈坤
 * @创建日期 2018/5/11
 * @功能描述 SpringSecurity的配置类
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Bean
    @Primary
    public DefaultWebInvocationPrivilegeEvaluator customWebInvocationPrivilegeEvaluator()
    {return new DefaultWebInvocationPrivilegeEvaluator(myFilterSecurityInterceptor);}

    @Bean
    UserDetailsService myUserDetailService(){return new MyUserDetailService();}     //注册UserDetailService的bean

    @Bean
    public MyAuthenticationSuccesshandler myAuthenticationSuccesshandler(){return new MyAuthenticationSuccesshandler();}    //登录成功后跳转路径管理


//    public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(4);}

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/5/14 9:30
     * @功能描述	身份验证配置，用于注入自定义身份验证Bean和密码校验规则
     * @参数
     * @返回值
     *
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        //userDetailService验证
        auth.userDetailsService(myUserDetailService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/5/14 9:30
     * @功能描述	Request层面的配置，对应XML Configuration中的<http>元素
     * @参数
     * @返回值
     *
     */
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**","/fonts/**","/img/**","/js/**","plugins/**").permitAll()
                .anyRequest().authenticated()       //任何请求，登录后可以访问
                .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .permitAll()    //登录页面用户任意访问
                    .defaultSuccessUrl("/index")
                    .successHandler(myAuthenticationSuccesshandler())
                .and()
                .headers().frameOptions().sameOrigin()   //项目中用到iframe嵌入网页，然后用到springsecurity就被拦截了 浏览器报错  x-frame-options deny
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                .permitAll();  //注销行为任意访问
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/5/23 18:06
     * @功能描述	Web层面的配置，一般用来配置无需安全检查的路径
     * @参数
     * @返回值
     *
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.securityInterceptor(myFilterSecurityInterceptor);
        web.privilegeEvaluator(customWebInvocationPrivilegeEvaluator());
        // Spring Security should completely ignore URLs starting with /resources/
        web.ignoring().antMatchers("/resources/**");



    }

}
