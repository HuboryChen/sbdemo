package com.xtlh.sbdemo.util;

import java.io.Serializable;
import java.util.Map;

/**
 * @作者 陈坤
 * @创建日期 2018/5/16
 * @功能描述 分页排序查询条件等参数封装实体
 */

public class PageParams implements Serializable {
    //查询条数
    private int limit;
    //查询开始数据条数
    private int offset;
    //排序字段
    private String sort;
    //排序规则
    private String order;
    //条件查询字符串
    private Map queryparam;

    public int getLimit()
    {
        return limit;
    }

    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    public int getOffset()
    {
        return offset;
    }

    public void setOffset(int offset)
    {
        this.offset = offset;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getOrder()
    {
        return order;
    }

    public void setOrder(String order)
    {
        this.order = order;
    }

    public Map getQueryparam()
    {
        return queryparam;
    }

    public void setQueryparam(Map queryparam)
    {
        this.queryparam = queryparam;
    }
}
