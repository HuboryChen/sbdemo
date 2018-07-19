package com.xtlh.sbdemo.designpattern.observerpattern;

/**
 * @Author: 陈坤
 * @Description: 抽象主题角色
 * @Date: Created in ${Time} 2018/7/19
 * @Modified By:
 */
public interface Subject
{
    void addWatcher(Watcher watcher);	//添加观察者

    void removeWatcher(Watcher watcher);	//删除观察者

    void notify(String message);		//向登记过的观察者发送通知
}
