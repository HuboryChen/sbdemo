package com.xtlh.sbdemo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @作者 陈坤
 * @创建日期 2018/5/21
 * @功能描述    系统角色实体类
 */
@Entity
@Table(name = "sys_role")
public class SysRole implements Serializable{

    private Integer id;     //角色id
    private String name;    //角色名
    private List<User> users;

    @Id
    @Column(name = "id",nullable = false, unique = true,length = 11)
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "sys_role_user",
            joinColumns = {@JoinColumn(name = "sys_role_id")},
            inverseJoinColumns = {@JoinColumn(name = "sys_user_id")})
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
