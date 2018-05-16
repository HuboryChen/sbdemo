package com.xtlh.sbdemo.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者 陈坤
 * @创建日期 2018/5/16
 * @功能描述 bootstrap table返回数据结构封装
 */

public class PageBean implements Serializable{
    private long total;                         //总条数
    private List rows = new ArrayList();        //实体集合

    public PageBean(long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageBean() {}

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
