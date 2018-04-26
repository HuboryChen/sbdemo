package com.xtlh.sbdemo;

import com.xtlh.sbdemo.entity.User;
import com.xtlh.sbdemo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @作者 陈坤
 * @创建日期 2018/4/24
 * @功能描述 测试持久层是否可用
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SbdemoApplication.class)
public class DaoTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertUser()
    {
        User user = new User();
        user.setUsername("星通");
        user.setPassword("666666");
        user.setType("管理员");
        user.setStatus(0);

        User save = userRepository.save(user);
        System.out.println(save);
    }

}
