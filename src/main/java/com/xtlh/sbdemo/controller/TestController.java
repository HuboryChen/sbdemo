package com.xtlh.sbdemo.controller;

import com.xtlh.sbdemo.entity.RedisModel;
import com.xtlh.sbdemo.service.serviceimpl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @作者 陈坤
 * @创建日期 2018/6/5
 * @功能描述 Redis控制器测试
 */
@Controller
@RequestMapping("/redisTest")
public class TestController {
    @Autowired
    private RedisServiceImpl service;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public void test()
    {
        System.out.println("start......");
        RedisModel m = new RedisModel();
        m.setName("张三");
        m.setTel("111");
        m.setAddress("深圳1");
        m.setRedisKey("zhangsanKey01");
        service.put(m.getRedisKey(), m, -1);

        RedisModel m2 = new RedisModel();
        m2.setName("张三2");
        m2.setTel("222");
        m2.setAddress("深圳2");
        m2.setRedisKey("zhangsanKey02");
        service.put(m2.getRedisKey(), m2, -1);

        RedisModel m3 = new RedisModel();
        m3.setName("张三3");
        m3.setTel("333");
        m3.setAddress("深圳3");
        m3.setRedisKey("zhangsanKey03");
        service.put(m3.getRedisKey(), m3, -1);

        System.out.println("add success end...");
    }


    //查询所有对象
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public Object getAll()
    {
//        System.out.println(new JSONObject().toJSONString(service.getAll())+"============================================");
        return service.getAll();
    }

    //查询所有key
    @RequestMapping(value = "/getKeys", method = RequestMethod.GET)
    @ResponseBody
    public Object getKeys() {
        return service.getKeys();
    }

    //根据key查询
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Object get() {
        RedisModel m = new RedisModel();
        m.setRedisKey("zhangsanKey02");
        return service.get(m.getRedisKey());
    }

    //删除
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    @ResponseBody
    public void remove() {
        RedisModel m = new RedisModel();
        m.setRedisKey("zhangsanKey01");
        service.remove(m.getRedisKey());
    }

    //判断key是否存在
    @RequestMapping(value = "/isKeyExists", method = RequestMethod.GET)
    @ResponseBody
    public void isKeyExists() {
        RedisModel m = new RedisModel();
        m.setRedisKey("zhangsanKey01");
        boolean flag = service.isKeyExists(m.getRedisKey());
        System.out.println("zhangsanKey01 是否存在: "+flag);
    }

    //查询当前缓存的数量
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public Object count() {
        return service.count();
    }

    //清空所有key
    @RequestMapping(value = "/empty", method = RequestMethod.GET)
    @ResponseBody
    public void empty() {
        service.empty();
    }
}
