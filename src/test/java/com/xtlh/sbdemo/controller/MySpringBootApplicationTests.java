package com.xtlh.sbdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xtlh.sbdemo.util.LogUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @作者 陈坤
 * @创建日期 2018/7/3
 * @功能描述 测试log4j2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MySpringBootApplicationTests.class)
public class MySpringBootApplicationTests {

    @Test
    public void contextLoads() {
        Logger platformLogger = LogUtils.getPlatformLogger();
        Logger exceptionLogger = LogUtils.getExceptionLogger();
        Logger bussinessLogger = LogUtils.getBussinessLogger();
        Logger dbLogger = LogUtils.getDBLogger();

        platformLogger.trace("getPlatformLogger==============日志测试");
        exceptionLogger.error("getExceptionLogger===============日志测试");
        bussinessLogger.info("getBussinessLogger===============日志测试");
        dbLogger.debug("getDBLogger===============日志测试");
    }
}
