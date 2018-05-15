package com.xtlh.sbdemo.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @作者 陈坤
 * @创建日期 2018/5/11
 * @功能描述 处理URL与权限的关系
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

//    @Autowired
//    private PermissionDao permissionDao;

    private HashMap<String, Collection<ConfigAttribute>> map = null;    //注入权限类持久层

    //构造函数中初始化loadResourceDefine()，tomcat启动时实例化一次
    public MyInvocationSecurityMetadataSourceService(){loadResourceDefine();}

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
        //测试：方法中定义
        map = new HashMap<>();
        ConfigAttribute cfg;
        Collection<ConfigAttribute> array = new ArrayList<ConfigAttribute>();
        ConfigAttribute ca = new SecurityConfig("ROLE_USER");
        array.add(ca);
        map.put("/userList.html", array);

        Collection<ConfigAttribute> arrayno = new ArrayList<ConfigAttribute>();
        ConfigAttribute cano = new SecurityConfig("ROLE_NO");
        arrayno.add(cano);
        map.put("/other.html", arrayno);


        //生产：从数据库获取
    /*
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;

        List<Permission> permissions = permissionDao.findAll();   //从数据库表中查找所有的权限信息
        for (Permission permission : permissions)
        {
            array = new ArrayList();
            cfg = new SecurityConfig(permission.getName());
            array.add(cfg);
            map.put(permission.getUrl, array);
        }*/

    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
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
