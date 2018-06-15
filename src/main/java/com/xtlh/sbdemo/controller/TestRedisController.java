package com.xtlh.sbdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.xtlh.sbdemo.entity.User;
import com.xtlh.sbdemo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者 陈坤
 * @创建日期 2018/6/7
 * @功能描述 测试Redis缓存控制器
 */
@Controller
@RequestMapping("/testRedis")
public class TestRedisController {

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save(Model model)
    {
        List<User> list = new ArrayList<User>();
        for(int i=0;i<10;i++)
        {
            User user = new User();
            user.setUsername("a"+i);
            user.setPassword("p"+i);
            list.add(user);
        }
        redisUtil.lSet("users",list);
        List<Object> objs =  redisUtil.lGet("users", 0, -1);
        System.out.println(new JSONObject().toJSONString(objs));
//        redisUtil.set("test","the data is redis for test");
//        String test = new JSONObject().toJSONString(redisUtil.get("test"));
//        model.addAttribute("test",test);
        return "index_v2";
    }

    @RequestMapping(value = "/toModify", method = RequestMethod.POST)
    public String toModify(Model model,String key)
    {
        Object test = redisUtil.get(key);
        model.addAttribute("test",test);
        return "testRedisAdd";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(Model model,String key, String value)
    {
        redisUtil.set(key,value);
        Object test = redisUtil.get("test");
        model.addAttribute("test",test);
        return "index_v2";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(String key)
    {
        redisUtil.del(key);
        return "redirect:getInfo";
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public String getRedisInfo(Model model)
    {
        Object test = redisUtil.get("test");
        model.addAttribute("test",test);
        return "index_v2";
    }
}
