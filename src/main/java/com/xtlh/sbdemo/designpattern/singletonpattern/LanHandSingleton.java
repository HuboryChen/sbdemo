package com.xtlh.sbdemo.designpattern.singletonpattern;

/**
 * @Author:
 * @Date: Created in ${Time} 2018/7/19
 * @Modified By:
 * *@Description: 单例模式--懒汉式
 *                getInstance 添加了synchronized关键字，也就是说getInstance是一个同步方法，这就是懒汉式在多线程中保持唯一性的方式
 *                （缺点：懒汉式存在为问题是，即使instance已经被创建了，每次调用getInstance方法还是会同步，这样就会浪费一些不必要的资源）
 */
public class LanHandSingleton
{
    private static LanHandSingleton instance;

    private LanHandSingleton(){}

    public static synchronized LanHandSingleton getInstance()
    {
        if(instance == null)
        {
            instance = new LanHandSingleton();
        }
        return instance;
    }
}
