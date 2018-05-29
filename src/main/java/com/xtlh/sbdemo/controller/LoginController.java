package com.xtlh.sbdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @作者 陈坤
 * @创建日期 2018/5/14
 * @功能描述 登录控制器
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    public String toLogin()
    {

        return "login";
    }


}
