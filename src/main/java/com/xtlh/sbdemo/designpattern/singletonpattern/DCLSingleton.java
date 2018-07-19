package com.xtlh.sbdemo.designpattern.singletonpattern;

/**
 * @Author:
 * @Date: Created in ${Time} 2018/7/19
 * @Modified By:
 * @Description: 单例模式--DCL式（Double Check Lock）
 *              getInstance进行了两次判空，
 *              第一次判空是为了不必要的同步，
 *              第二次判空是为了在instance为null的情况下创建实例，
 *              既保证了线程安全且单例对象初始化后调用getInstance又不会进行同步锁判断。
 *              优点：资源利用率高，效率高；
 *              缺点：第一次加载稍慢，由于Java处理器允许乱序执行，偶尔会失败。
 */
public class DCLSingleton
{
    private static DCLSingleton instance;

    private DCLSingleton(){}

    public static DCLSingleton getSingleton()
    {
        if (instance == null)
        {
            synchronized (DCLSingleton.class)
            {
                if(instance == null)
                {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}
