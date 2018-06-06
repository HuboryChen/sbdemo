package com.xtlh.sbdemo.service.serviceimpl;

import com.xtlh.sbdemo.entity.RedisModel;
import com.xtlh.sbdemo.service.IRedisService;
import org.springframework.stereotype.Service;

/**
 * @作者 陈坤
 * @创建日期 2018/6/5
 * @功能描述 Redis测试业务层实现类
 */
@Service
public class RedisServiceImpl extends IRedisService<RedisModel> {
    private static final String REDIS_KEY = "TEST_REDIS_KEY";

    @Override
    protected String getRedisKey() {
        return this.REDIS_KEY;
    }
}
