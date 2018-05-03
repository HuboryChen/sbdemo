package com.xtlh.sbdemo.entity;

import javax.persistence.*;

/**
 * @作者 陈坤
 * @创建日期 2018/4/24
 * @功能描述 用户实体类
 */
@Entity
public class User {
    private Long id;    //自动id
    private String username;    //用户名
    private String password;    //用户密码
    private String type;        //用户类型
    private int status;         //账户状态

    public User(){}         //空构造函数

    public User(String username, String password, String type, int status) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.status = status;
    }

    @Id
    @Column(name = "id",nullable = false, unique = true,length = 11)
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username",nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password",nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "type",nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "status",nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
