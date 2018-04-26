package com.xtlh.sbdemo.service.serviceimpl;

import com.xtlh.sbdemo.entity.User;
import com.xtlh.sbdemo.repository.UserRepository;
import com.xtlh.sbdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @作者 陈坤
 * @创建日期 2018/4/24
 * @功能描述 用户业务逻辑实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    /**
     *
     * @param page:分页器
     * @return  分页对象
     */
    @Override
    public Page<User> findAll(int page) {
        Pageable pageable = new PageRequest(page-1, 3, new Sort(Sort.Direction.ASC, "id"));
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }

    /*@Override
    public Page<User> findSearch(String username, Pageable pageable) {
        return null;
    }*/
}
