package com.xtlh.sbdemo.designpattern.observerpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:
 * @Description: 具体主题角色
 * @Date: Created in ${Time} 2018/7/19
 * @Modified By:
 */
public class ConcreteSubject implements Subject
{
    private List<Watcher> watchers = new ArrayList<Watcher>();


    public void addWatcher(Watcher watcher)
    {
        watchers.add(watcher);
    }

    public void removeWatcher(Watcher watcher)
    {
        watchers.remove(watcher);
    }

    public void notify(String message)
    {
        for(Watcher watcher : watchers)
        {
            watcher.update(message);
        }
    }
}
