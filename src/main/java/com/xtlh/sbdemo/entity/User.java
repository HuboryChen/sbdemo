package com.xtlh.sbdemo.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @作者 陈坤
 * @创建日期 2018/4/24
 * @功能描述 用户实体类
 */
@Entity
@Table(name = "user")
public class User{
    private Long id;    //自动id
    private String username;    //用户名
    private String password;    //用户密码
    private String type;        //用户类型
    private int status;         //账户状态

    private List<SysRole> roles;

    public User(){}         //空构造函数

    @Id
    @Column(name = "id",nullable = false, unique = true,length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
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

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_user",
        joinColumns = {@JoinColumn(name = "sys_user_id")},
        inverseJoinColumns = {@JoinColumn(name = "sys_role_id")})
    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
