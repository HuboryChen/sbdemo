package com.xtlh.sbdemo.entity;

import java.io.Serializable;

/**
 * @作者 陈坤
 * @创建日期 2018/6/5
 * @功能描述 Redis测试模板类
 */

public class RedisModel implements Serializable {
    private String redisKey;    //redis中的key
    private String name;    //姓名
    private String tel;     //电话
    private String address; //住址

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
