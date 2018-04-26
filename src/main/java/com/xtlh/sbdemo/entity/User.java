package com.xtlh.sbdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @作者 陈坤
 * @创建日期 2018/4/24
 * @功能描述 用户实体类
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;    //自动id

    @Column(nullable = false, unique = true)
    private String username;    //用户名

    @Column(nullable = false)
    private String password;    //用户密码

    @Column(nullable = false)
    private String type;        //用户类型

    @Column(nullable = false)
    private int status;         //账户状态

    public User(){}         //空构造函数

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
