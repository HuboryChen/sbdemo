package com.xtlh.sbdemo.config;

import com.xtlh.sbdemo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @作者 陈坤
 * @创建日期 2018/6/5
 * @功能描述 Redis配置
 */
@Configuration
@EnableCaching
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RedisConfig extends CachingConfigurerSupport{

    private static JedisPool jedisPool = null;

    @Value("${spring.redis.host}")
    private  String host;         //Redis服务器地址

    @Value("${spring.redis.port}")
    private  int port;         //Redis服务器端口

    @Value("${spring.redis.password}")
    private  String password;     //Redis服务器密码

    @Value("${spring.redis.timeout}")   //连接超时时间（毫秒）
    private  int timeout;


    /**
     * 
     * @作者		陈坤
     * @创建日期	2018/6/7 12:05
     * @功能描述	JedisConnectionFactory 配置
     * @参数  
     * @返回值
     *
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig)
    {
        System.out.println("JedisConnectionFactory start...........................");
        System.out.println("host="+host+",port="+port+",password="+password+",timeout="+timeout);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);  //连接池
        jedisConnectionFactory.setHostName(host);       //服务器地址
        jedisConnectionFactory.setPort(port);           //端口号
        jedisConnectionFactory.setPassword(password);   //密码
        jedisConnectionFactory.setTimeout(timeout);     //客户端超时时间（单位是毫秒）
        return jedisConnectionFactory;
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/6 10:29
     * @功能描述	注入配置文件中的JedisPoolConfig配置
     * @参数
     * @返回值
     *
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.redis.jedis.pool")
    public static  JedisPoolConfig getRedisConfig()
    {
        System.out.println("JedisPoolConfig start...........................");
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }


    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 15:41
     * @功能描述	实例化RedisTemplate对象
     * @参数
     * @返回值
     *
     */
    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 15:43
     * @功能描述	设置数据存入redis的序列化方式，并开启事务
     * @参数     RedisTemplate
     * @参数      factory
     * @返回值     空值
     *
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory)
    {
        /*
        JdkSerializationRedisSerializer——使用Java自带的序列化机制将对象序列化为一个字符串
        OxmSerializer——将对象序列化为xml字符串
        Jackson2JsonRedisSerializer——将对象序列化为json字符串
        */
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

//        redisTemplate.setEnableTransactionSupport(true);        //开启事务
        redisTemplate.setConnectionFactory(factory);
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/7 12:12
     * @功能描述	注入封装RedisTemplate
     * @参数
     * @返回值
     *
     */
    @Bean(name = "redisUtil")
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate)
    {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }

}
