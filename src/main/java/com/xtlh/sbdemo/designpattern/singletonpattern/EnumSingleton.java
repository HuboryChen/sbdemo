package com.xtlh.sbdemo.designpattern.singletonpattern;

/**
 * @Author:
 * @Date: Created in ${Time} 2018/7/19
 * @Modified By:
 * @Description: 单例模式--枚举实现单例模式
 *                  默认枚举实例的创建时线程安全的，且任何一种情况下他都是单例，包括反序列化。
 */
public enum  EnumSingleton
{
    INSTANCE;
}
