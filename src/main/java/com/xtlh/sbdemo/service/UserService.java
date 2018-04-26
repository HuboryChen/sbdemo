package com.xtlh.sbdemo.service;

import com.xtlh.sbdemo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface UserService{
    //全部查询
    Page<User> findAll(int page);

    //根据条件分页查询用户
    public Page<User> findByCondition(String username, String type, int page);

    //根据多种信息模糊查询用户
   // Page<User> findSearch(String username, Pageable pageable);

}
