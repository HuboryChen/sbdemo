package com.xtlh.sbdemo.designpattern.singletonpattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:
 * @Date: Created in ${Time} 2018/7/19
 * @Modified By:
 * @Description: 单例模式--使用容器实现单例模式
 *                  将多种类型的单例放到统一的Map集合中，根据相应的key获取对应的对象
 *                  （优点：使我们可以管理多种类型的单例，可以使用统一接口进行获取操作，降低了使用成本，也隐藏了具体实现，降低了耦合度。）
 */
public class ContainerSingleton
{
    public static Map<String, Object> objMap = new HashMap<String, Object>();

    private ContainerSingleton(){}

    public static void registerInstance(String key, Object instance)
    {
        if (!objMap.containsKey(key))
        {
            objMap.put(key, instance);
        }
    }

    public static Object getInstance(String key)
    {
        return objMap.get(key);
    }

}
