package com.xtlh.sbdemo.controller;

import com.xtlh.sbdemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @作者 陈坤
 * @创建日期 2018/5/25
 * @功能描述 角色控制器
 */
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/5/25 10:41
     * @功能描述	查询所有角色
     * @参数
     * @返回值
     *
     */
    @RequestMapping("/roles")
    @ResponseBody
    public String findAll()
    {
        System.out.println(roleService.findAll());
        return roleService.findAll();
    }
}
