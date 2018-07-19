package com.xtlh.sbdemo.designpattern.singletonpattern;

/**
 * @Author:
 * @Date: Created in ${Time} 2018/7/19
 * @Modified By:
 * @Description: 单例模式---饿汉式
 *                 特点：是在声明的时候就已经初始化Singleton1,确保了对象的唯一性(缺点：声明的时候就初始化对象会浪费不必要的资源
 */
public class EHanSingleton {
    private static EHanSingleton instance = new EHanSingleton();

    private EHanSingleton(){}

    public static EHanSingleton getInstance()
    {
        return instance;
    }

}
