package com.xtlh.sbdemo.controller;

import com.xtlh.sbdemo.entity.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @作者 陈坤
 * @创建日期 2018/5/11
 * @功能描述 Security测试目录
 */
@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home(Model model){
        Msg msg =  new Msg("测试标题","测试内容","欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
        model.addAttribute("msg", msg);
        return "home";
    }


    @RequestMapping("/admin")
    @ResponseBody
    public String hello(){
        return "hello admin";
    }

    @RequestMapping("/other")
    public String other(){
        return "other";
    }
}
