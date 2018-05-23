package com.xtlh.sbdemo.security;

import com.xtlh.sbdemo.entity.SysPermission;
import com.xtlh.sbdemo.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @作者 陈坤
 * @创建日期 2018/5/11
 * @功能描述 处理URL与权限的关系
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Qualifier("permissionRepository")
    @Autowired
    private PermissionRepository permissionRepository;  //注入权限类持久层

    private HashMap<String, Collection<ConfigAttribute>> map = null;

    //构造函数中初始化loadResourceDefine()，tomcat启动时实例化一次
    public MyInvocationSecurityMetadataSourceService(){}

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/5/11 15:46
     * @功能描述	tomcat开启时加载一次，加载权限表中所有url和权限（或角色）的对应关系
     * （   优点：一次性加载后，进行缓存，提高性能。
     *
     *      缺点：因为loadResourceDefine方法是放在构造器上调用的，而这个类的实例化只在web服务器启动时调用一次，
     *            那就是说loadResourceDefine方法只会调用一次，如果资源和权限的对应关系在启动后发生了改变，那么缓存起来的就是脏数据。
     *
     *      解决方案：若要允许资源和权限的对应关系改变，则不需要loadResourceDefine方法了，
     *          直接在getAttributes方法里面调用dao（这个是加载完，后来才会调用的，所以可以使用dao，
     *          通过被拦截url获取数据库中的所有权限，封装成Collection<ConfigAttribute>返回就行了）
     * @参数
     * @返回值
     *
     */
    public void loadResourceDefine()
    {
        //生产：从数据库获取
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;

        List<SysPermission> permissions = permissionRepository.findAll();   //从数据库表中查找所有的权限信息
        for (SysPermission permission : permissions)
        {
            array = new ArrayList();
            cfg = new SecurityConfig(permission.getName());
            //此处指添加了权限名，其实还可以添加更多地权限信息，例如请求方法到ConfigAttribute的集合中去
            array.add(cfg);
            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value
            map.put(permission.getUrl(), array);
        }

    }

    /**
     * 
     * @作者		陈坤
     * @创建日期	2018/5/22 16:37
     * @功能描述	判断用户请求的Url是否在权限表中
     *              （如果在权限表中，则返回给decide方法，用来判定用户是否有此权限；
     *                如果不在权限表中，则放行。）
     * @参数  object：用户请求
     * @返回值
     *
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(map == null) this.loadResourceDefine();
        //object中包含用户请求的request信息
        HttpServletRequest request =((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); )
        {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request))
            {
                System.out.println("map.get(resUrl)========================="+map.get(resUrl));
                return map.get(resUrl);
            }
        }
        return null;
    }



    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
