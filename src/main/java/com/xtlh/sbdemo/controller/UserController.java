package com.xtlh.sbdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.xtlh.sbdemo.entity.User;
import com.xtlh.sbdemo.service.UserService;
import com.xtlh.sbdemo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    RedisUtil redisUtil;

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
        return "userList1";
    }

    /**
     * 分页条件查询用户列表
     * @param model 视图模型
     * @param username  用户名
     * @param type  用户类型
     * @param page  当前页
     * @return 用户列表分页对象
     */
    @RequestMapping(value = "/usersbycondition",method = RequestMethod.GET)
    public String userListBycondition(Model model, @RequestParam(name = "username", defaultValue = "") String username,
                                       @RequestParam(name = "type", defaultValue = "") String type,
                                       @RequestParam(name = "page", defaultValue = "1")int page)
    {
        Page<User> userPage = userService.findByCondition(username, type, page);
        model.addAttribute("userPage",userPage);
        return "userList";
    }

    @RequestMapping(value="/userPageInfo",method = RequestMethod.POST)
    @ResponseBody
    public String pageInfo(@RequestParam(name = "pageNumber") int pageNumber,@RequestParam(name = "pageSize") int pageSize)
    {
        return userService.findByCondition(pageNumber,pageSize);
    }


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
    @ResponseBody
    public String saveUser(@ModelAttribute(value = "user")  User user)
    {
        JSONObject jsonObject = new JSONObject();
        System.out.println("user表单数据"+jsonObject.toJSONString(user));
        userService.saveUser(user);
        return "success";
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/5/18 16:58
     * @功能描述	跳转到修改页面
     * @参数
     * @返回值
     *
     */
    @RequestMapping(value = "/toModify", method = RequestMethod.GET)
    public String toModify(Long id, Model model)
    {
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "userAdd";
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/5/10 9:51
     * @功能描述	删除用户
     * @参数
     * @返回值
     *
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String deleteUser(Long id)
    {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/5/18 16:54
     * @功能描述	批量删除用户
     * @参数
     * @返回值
     *
     */
    @RequestMapping(value = "/deleteInBatchUser",method = RequestMethod.POST)
    @ResponseBody
    public String deleteInBatchUser(@RequestBody List<User> requestList)
    {
        userService.deleteInBatch(requestList);
        return "success";
    }

}
