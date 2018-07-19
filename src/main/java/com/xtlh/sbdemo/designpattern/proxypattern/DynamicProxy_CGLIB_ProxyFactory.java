package com.xtlh.sbdemo.designpattern.proxypattern;

import org.hibernate.proxy.ProxyFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author:
 * @Date: Created in ${Time} 2018/7/19
 * @Modified By:
 * @Description: 代理模式--cglib动态代理--cglib子类代理工厂
 *              对UserDao在内存中动态构建一个子类对象
 *
 */
public class DynamicProxy_CGLIB_ProxyFactory implements MethodInterceptor
{
    //维护目标
    private Object target;

    public DynamicProxy_CGLIB_ProxyFactory(Object target)
    {
        this.target = target;
    }

    //给目标创建一个代理对象
    public Object getProxyInstance()
    {
        //1、工具类
        Enhancer en = new Enhancer();

        //2、设置父类
        en.setSuperclass(target.getClass());

        //3、设置回调函数
        en.setCallback(this);

        //4、创建子类（代理对象
        return en.create();

    }


    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable
    {
        System.out.println("开始事务3...............");

        //执行目标对象方法
        Object returnValue = method.invoke(target, args);

        System.out.println("提交事务3..........");
        return returnValue;
    }
}
