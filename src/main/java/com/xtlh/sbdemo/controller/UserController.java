package com.xtlh.sbdemo.controller;

import com.xtlh.sbdemo.entity.User;
import com.xtlh.sbdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @作者 陈坤
 * @创建日期 2018/4/24
 * @功能描述 用户控制器
 */
//@RestController
@Controller
public class UserController {
    @Autowired
    UserService userService;

    /*@RequestMapping(value = "/users", method = RequestMethod.GET)
    private String userList(Model model)
    {
        List<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        return "index";
    }*/

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    private String userList(Model model, @RequestParam(name="page",defaultValue = "1")int page)
    {
        System.out.println("page========================"+page);
        Page<User> userPage = userService.findAll(page);
        model.addAttribute("userPage",userPage);
        return "index";
    }

    /*@GetMapping(value = "/users")
    private Page<User> userList(@RequestParam(value = "page")int page)
    {
        Page<User> userPage = userService.findAll(page);
        return userPage;
    }*/

   /* @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    private String toAdd()
    {
        return "userAdd";
    }

    private String userSave(User user)
    {
        return "index";
    }*/
}
