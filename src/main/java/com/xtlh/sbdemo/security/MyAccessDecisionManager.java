package com.xtlh.sbdemo.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 * @作者 陈坤
 * @创建日期 2018/5/11
 * @功能描述 授权管理器
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager{
    /**
     *
     * @作者		陈坤
     * @创建日期	2018/5/11 16:29
     * @功能描述	decide判断是否拥有权限的决策方法
     * @参数  authentication：是MyUserDetailService中循环添加到GrantedAuthority对象中的权限信息集合
     * @参数  object: 包含客户端发起的请求的request信息，
     *              可转换为HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
     * @参数  configAttributes:为MyInvocationSecurityMetadataSource的getAttributes(Object object) 这个方法返回的结果，
     *              此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。
     *              如果不在权限表中则放行。
     * @返回值
     *
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if(null == configAttributes || configAttributes.size() <= 0)
        {
            return;
        }

        ConfigAttribute c;
        String needRole;
        for (Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext();)
        {
            c = iter.next();
            needRole = c.getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) //authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
            {
                if(needRole.trim().equals(ga.getAuthority()))
                {
                    return;
                }
            }
        }
        throw new AccessDeniedException("No right");

    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
