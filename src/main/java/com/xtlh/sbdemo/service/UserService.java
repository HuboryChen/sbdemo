package com.xtlh.sbdemo.service;

import com.xtlh.sbdemo.entity.User;
import com.xtlh.sbdemo.util.PageBean;
import com.xtlh.sbdemo.util.PageParams;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService{

    Page<User> findAll(int page);       //全部查询

    Page<User> findByCondition(String username, String type, int page);     //根据条件分页查询用户

   // Page<User> findSearch(String username, Pageable pageable);            //根据多种信息模糊查询用户


    User saveUser(User user);           //添加用户

    void deleteUser(Long id);           //删除用户

    User findById(Long id);             //通过ID查找用户

    PageBean findForPage(PageParams params);

    String findByCondition(int pageNumber, int pageSize);

    void deleteInBatch(List<User> list); //批量删除用户
}
