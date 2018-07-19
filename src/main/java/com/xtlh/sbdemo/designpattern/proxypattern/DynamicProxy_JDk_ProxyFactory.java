package com.xtlh.sbdemo.designpattern.proxypattern;

import org.hibernate.proxy.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author:
 * @Date: Created in ${Time} 2018/7/19
 * @Modified By:
 * @Description:  JDK动态代理-代理类工厂，创建动态代理对象
 *                  动态代理不需要实现接口，但是需要制定接口类型
 */
public class DynamicProxy_JDk_ProxyFactory
{
    //维护创建一个目标
    private Object target;

    public DynamicProxy_JDk_ProxyFactory(Object target)
    {
        this.target = target;
    }

    //给目标对象生成代理对象
    public Object getProxyInstance()
    {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),     //当前目标对象使用类加载器
                target.getClass().getInterfaces(),      //目标对象实现的接口的类型,使用泛型方式确认类型
                new InvocationHandler() {   //事件处理,执行目标对象的方法时,会触发事件处理器的方法,会把当前执行目标对象的方法作为参数传入
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
            {
                System.out.println("开始事务2");
                //执行目标对象方法
                Object returnValue = method.invoke(target, args);
                System.out.println("提交事务2");
                return returnValue;
            }
        });
    }
}
