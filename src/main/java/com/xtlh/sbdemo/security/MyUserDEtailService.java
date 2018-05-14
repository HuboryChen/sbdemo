package com.xtlh.sbdemo.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者 陈坤
 * @创建日期 2018/5/11
 * @功能描述 获取用户验证信息
 */
@Service
public class MyUserDetailService implements UserDetailsService{


    /**
     *
     * @作者		陈坤
     * @创建日期	2018/5/11 15:04
     * @功能描述	登录验证时，通过username获取用户的所有权限信息，并返回User到Spring的全局缓存SecurityContextHolder中
     * @参数  用户名
     * @返回值 包含权限信息的User对象
     *
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,DataAccessException {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

        if(username.equals("admin"))
        {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        String pw = new BCryptPasswordEncoder().encode("123456");
        User user = new User(username, pw, true, true, true, true, authorities);
        return user;
    }
}
