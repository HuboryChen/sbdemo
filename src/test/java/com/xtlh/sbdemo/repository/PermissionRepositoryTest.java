package com.xtlh.sbdemo.repository;

import com.xtlh.sbdemo.SbdemoApplication;
import com.xtlh.sbdemo.entity.SysPermission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @作者 陈坤
 * @创建日期 2018/5/22
 * @功能描述 权限数据层测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SbdemoApplication.class)
@WebAppConfiguration
public class PermissionRepositoryTest {
    @Autowired
    PermissionRepository permissionRepository;

    @Test
    public void testFindAll()
    {
        List<SysPermission> all = permissionRepository.findAll();
        System.out.println(all.size()+"===========================");
    }
}
