package com.xtlh.sbdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @作者 陈坤
 * @创建日期 2018/6/5
 * @功能描述 Redis测试业务层
 */


public abstract class IRedisService<T> {
    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    @Resource
    protected HashOperations<String, String, T> hashOperations;

    /**
     * 存入redis中的key
     * @return
     */
    protected abstract String getRedisKey();


    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 16:34
     * @功能描述	添加
     * @参数  key
     * @参数  domain对象
     * @参数  expire 过期时间（单位：秒），传入 -1 时表示不设置过期时间
     * @返回值
     *
     */
    public void put(String key, T domain, long expire)
    {
        hashOperations.put(getRedisKey(), key, domain);
        if(expire != -1)
        {
            redisTemplate.expire(getRedisKey(), expire, TimeUnit.SECONDS);
        }
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 16:36
     * @功能描述	删除
     * @参数  key
     * @返回值 无
     *
     */
    public void remove(String key)
    {
        hashOperations.delete(getRedisKey(),key);
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 16:38
     * @功能描述	查询
     * @参数  key 查询的key
     * @返回值
     *
     */
    public T get(String key)
    {
        return hashOperations.get(getRedisKey(), key);
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 16:40
     * @功能描述	获取当前redis库下所有的对象
     * @参数
     * @返回值
     *
     */
    public List<T> getAll()
    {
        return hashOperations.values(getRedisKey());
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 16:41
     * @功能描述	查询当前redis库下所有key
     * @参数
     * @返回值
     *
     */
    public Set<String> getKeys()
    {
        return hashOperations.keys(getRedisKey());
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 16:42
     * @功能描述	判断key是否存在redis中
     * @参数  key 传入的key的名称
     * @返回值
     *
     */
    public boolean isKeyExists(String key)
    {
        return hashOperations.hasKey(getRedisKey(), key);
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 16:43
     * @功能描述	查询当前key下缓存数量
     * @参数
     * @返回值
     *
     */
    public long count()
    {
        return hashOperations.size(getRedisKey());
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 16:47
     * @功能描述	清空redis
     * @参数
     * @返回值
     *
     */
    public void empty()
    {
        Set<String> set = hashOperations.keys(getRedisKey());
        set.stream().forEach(key -> hashOperations.delete(getRedisKey(), key));
    }
}
