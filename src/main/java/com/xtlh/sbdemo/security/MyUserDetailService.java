package com.xtlh.sbdemo.security;

import com.xtlh.sbdemo.entity.SysPermission;
import com.xtlh.sbdemo.entity.User;
import com.xtlh.sbdemo.repository.PermissionRepository;
import com.xtlh.sbdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.security.core.userdetails.User;

/**
 * @作者 陈坤
 * @创建日期 2018/5/11
 * @功能描述 获取用户验证信息
 */
@Service
public class MyUserDetailService implements UserDetailsService{
@Qualifier("userRepository")
    @Autowired
    UserRepository userRepository;

    @Qualifier("permissionRepository")
    @Autowired
    PermissionRepository permissionRepository;

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
        User user = userRepository.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("该用户不存在！");
        }
        List<SysPermission> permissions = permissionRepository.findByAdminUserId(user.getId());

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (SysPermission permission : permissions)
        {
            if(permission != null && permission.getName() != null)
            {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                //1：此处将权限信息添加到GrantedAuthority对象中，在后面进行权限验证时会使用GrantedAuthority对象
                grantedAuthorities.add(grantedAuthority);
            }

        }

//        String pw = new BCryptPasswordEncoder().encode(user.getPassword());
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, grantedAuthorities);
    }
}
