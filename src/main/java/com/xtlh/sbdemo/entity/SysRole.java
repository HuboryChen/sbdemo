package com.xtlh.sbdemo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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
//    private Set<User> users;
    private Set<SysPermission> permissions;

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

    /*@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "sys_role_user",
            joinColumns = {@JoinColumn(name = "sys_role_id")},
            inverseJoinColumns = {@JoinColumn(name = "sys_user_id")})
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {this.users = users;    }
*/

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "sys_permission_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    public Set<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<SysPermission> permissions) {
        this.permissions = permissions;
    }
}
