package com.xtlh.sbdemo.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @作者 陈坤
 * @创建日期 2018/5/22
 * @功能描述 权限实体类
 */
@Entity
@Table(name = "sys_permission")
public class SysPermission implements Serializable{
    private Integer id;     //权限id
    private String name;    //权限名
    private String description;     //权限描述
    private String url;     //授权链接
    private Integer pid;    //父节点id


    @Id
    @Column(name = "id",nullable = false, unique = true,length = 11)
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "pid")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
