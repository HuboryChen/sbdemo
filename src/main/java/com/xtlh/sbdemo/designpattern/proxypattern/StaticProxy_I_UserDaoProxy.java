package com.xtlh.sbdemo.designpattern.proxypattern;

/**
 * @Author:
 * @Description:
 * @Date: Created in ${Time} 2018/7/19
 * @Modified By:
 */
public class StaticProxy_I_UserDaoProxy implements IUserDao
{
    //接收保存目标对象
    private IUserDao target;

    public StaticProxy_I_UserDaoProxy(IUserDao target)
    {
        this.target = target;
    }

    @Override
    public void save()
    {
        System.out.println("开始事务...........");
        target.save();  //执行目标对象的方法
        System.out.println("提交事务...........");
    }
}
