package com.xtlh.sbdemo.designpattern.proxypattern;

/**
 * @Author:
 * @Date: Created in ${Time} 2018/7/19
 * @Modified By:
 * @Description: 静态代理--接口实现-目标对象
 */
public class UserDao implements IUserDao
{
    @Override
    public void save()
    {
        System.out.println("--已经保存数据--");
    }
}
