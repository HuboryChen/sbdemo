package com.xtlh.sbdemo.service;

import com.xtlh.sbdemo.entity.User;
import com.xtlh.sbdemo.util.PageBean;
import com.xtlh.sbdemo.util.PageParams;
import org.springframework.data.domain.Page;

public interface UserService{
    //全部查询
    Page<User> findAll(int page);

    //根据条件分页查询用户
    public Page<User> findByCondition(String username, String type, int page);

    //根据多种信息模糊查询用户
   // Page<User> findSearch(String username, Pageable pageable);

    //添加用户
    public User saveUser(User user);

    //删除用户
    public void deleteUser(Long id);

    public User findById(Long id);


    PageBean findForPage(PageParams params);
}
