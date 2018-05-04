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
    public String userList(Model model)
    {
        List<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        return "index";
    }*/

    /**
     *
     * @param model :视图模型
     * @param page：当前页
     * @return 分页列表信息
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model, @RequestParam(name="page",defaultValue = "1")int page)
    {
        Page<User> userPage = userService.findAll(page);
        model.addAttribute("userPage",userPage);
        return "index.html";
    }

    /**
     * 分页条件查询用户列表
     * @param model 视图模型
     * @param username  用户名
     * @param type  用户类型
     * @param page  当前页
     * @return 用户列表分页对象
     */
    @RequestMapping(value = "usersbycondition",method = RequestMethod.GET)
    public String userListBycondition(Model model, @RequestParam(name = "username", defaultValue = "") String username,
                                       @RequestParam(name = "type", defaultValue = "") String type,
                                       @RequestParam(name = "page", defaultValue = "1")int page)
    {
        Page<User> userPage = userService.findByCondition(username, type, page);
        model.addAttribute("userPage",userPage);
        return "index";
    }



    /*@GetMapping(value = "/users")
    public Page<User> userList(@RequestParam(value = "page")int page)
    {
        Page<User> userPage = userService.findAll(page);
        return userPage;
    }*/

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/5/3 15:13
     * @功能描述	跳转到添加用户界面
     * @参数
     * @返回值
     *
     */
    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd()
    {
        return "userAdd";
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/5/3 15:13
     * @功能描述	添加用户，并重定向到/users
     * @参数
     * @返回值
     *
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String saveUser(@ModelAttribute(value = "user")  User user)
    {
        System.out.println(user.getUsername()+"==========================");
//        userService.saveUser(user);
        return "index";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String deleteUser(Long id)
    {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
