package com.xtlh.sbdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @作者 陈坤
 * @创建日期 2018/4/24
 * @功能描述 登录控制器
 */
@Controller
public class LoginController
{
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

}
