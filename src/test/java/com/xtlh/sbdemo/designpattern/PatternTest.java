package com.xtlh.sbdemo.designpattern;

import com.xtlh.sbdemo.designpattern.observerpattern.ConcreteSubject;
import com.xtlh.sbdemo.designpattern.observerpattern.ConcreteWatcher;
import com.xtlh.sbdemo.designpattern.observerpattern.Subject;
import com.xtlh.sbdemo.designpattern.observerpattern.Watcher;
import com.xtlh.sbdemo.designpattern.proxypattern.*;
import org.junit.Test;

/**
 * @Author:
 * @Description:    模式测试
 * @Date: Created in ${Time} 2018/7/19
 * @Modified By:
 */
public class PatternTest
{

    /**
     *@Author:
     *@Description: 测试观察者模式
     *@Date: Created in $time $date
     *
     */
    @Test
    public void TestOberverPattern()
    {
        Subject subject = new ConcreteSubject();

        Watcher watcher1 = new ConcreteWatcher();
        Watcher watcher2 = new ConcreteWatcher();
        Watcher watcher3 = new ConcreteWatcher();
        Watcher watcher4 = new ConcreteWatcher();

        subject.addWatcher(watcher1);
        subject.addWatcher(watcher2);
        subject.addWatcher(watcher3);
        subject.addWatcher(watcher4);

        subject.notify("hello baby");

        subject.removeWatcher(watcher4);
        subject.notify("bye~~");

    }

    /**
     *@Author:
     *@Description: 测试代理模式--静态代理
     *@Date: Created in $time $date
     *
     */
    @Test
    public void TestStaticProxyPattrn()
    {
        //目标对象
        UserDao target = new UserDao();

        //代理对象，把目标对象传给代理对象，建立代理关系
        StaticProxy_I_UserDaoProxy proxy = new StaticProxy_I_UserDaoProxy(target);

        proxy.save();   //执行的是代理的方法

    }

    /**
     *@Author:
     *@Description: 测试动态代理-JDK代理
     *@Date: Created in $time $date
     *
     */
    @Test
    public void TestDynamicProxy_JDK_ProxyFactory()
    {
        //目标对象
        IUserDao target = new UserDao();

        System.out.println(target.getClass());

        //给目标对象，创建代理对象
        IUserDao proxy = (IUserDao)new DynamicProxy_JDk_ProxyFactory(target).getProxyInstance();

        //class $Proxy0  内存中动态生成的代理对象
        System.out.println(proxy.getClass());

        //执行方法  【代理对象】
        proxy.save();

    }


    /**
     *@Author:
     *@Description: 测试动态代理-cglib代理
     *@Date: Created in $time $date
     *
     */
    @Test
    public void TestDynamicProxy_CGLIB_ProxyFactory()
    {
        //目标对象
        UserDao target = new UserDao();

        //代理对象
        UserDao proxy = (UserDao) new DynamicProxy_CGLIB_ProxyFactory(target).getProxyInstance();

        //执行代理对象方法
        proxy.save();

    }

}
