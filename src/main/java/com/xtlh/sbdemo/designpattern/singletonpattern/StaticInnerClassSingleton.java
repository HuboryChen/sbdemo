package com.xtlh.sbdemo.designpattern.singletonpattern;

/**
 * @Author:
 * @Date: Created in ${Time} 2018/7/19
 * @Modified By:
 * @Description: 单例模式--静态内部类实现单例模式
 *                  当第一次加载Singleton类时，并不会初始化SINGLETON，只有第一次调用getInstance方法的时候才会初始化Singleton，
 *                  第一次调用getInstance方法的时候虚拟机才会加载SingletonHoder类，
 *                  这种方式不仅能够保证线程安全，也能够保证对象的唯一，还延迟了单例的实例化，所以推荐使用这种方式。
 */
public class StaticInnerClassSingleton
{
    private StaticInnerClassSingleton(){}

    public StaticInnerClassSingleton getInstance()
    {
        return SingletonHolder.SINGLETON;
    }

    private static class SingletonHolder
    {
        private static StaticInnerClassSingleton SINGLETON = new StaticInnerClassSingleton();
    }

}
