package com.xtlh.sbdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @作者 陈坤
 * @创建日期 2018/4/26
 * @功能描述 测试权限控制路径跳转
 */
@Controller
public class DefaultController {
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/index")
    public String home() {
        return "/index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
