package com.xtlh.sbdemo.controller;

import com.xtlh.sbdemo.SbdemoApplication;
import com.xtlh.sbdemo.entity.User;
import com.xtlh.sbdemo.repository.UserRepository;
import com.xtlh.sbdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @作者 陈坤
 * @创建日期 2018/5/3
 * @功能描述 用户控制器测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SbdemoApplication.class)
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    public void saveUser()
    {
        User user = new User();
        user.setUsername("星通");
        user.setPassword("666666");
        user.setType("管理员");
        user.setStatus(0);

        userController.saveUser(user);

    }

}
