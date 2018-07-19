package com.xtlh.sbdemo.designpattern.observerpattern;

/**
 * @Author:
 * @Description: 具体观察者
 * @Date: Created in ${Time} 2018/7/19
 * @Modified By:
 */
public class ConcreteWatcher implements Watcher
{
    @Override
    public void update(String message)
    {
        System.out.println(this.toString() + ":" + message);
    }
}
