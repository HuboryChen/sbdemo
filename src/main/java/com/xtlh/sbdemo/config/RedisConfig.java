package com.xtlh.sbdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @作者 陈坤
 * @创建日期 2018/6/5
 * @功能描述 Redis配置
 */
@Configuration
@EnableAutoConfiguration
public class RedisConfig extends CachingConfigurerSupport{
//
//    private static JedisPool jedisPool = null;
//
//    @Value("${spring.redis.host}")
//    private static String host;         //Redis服务器地址
//
//    @Value("${spring.redis.port}")
//    private static int port;         //Redis服务器端口
//
//    @Value("${spring.redis.password}")
//    private static String password;     //Redis服务器密码
//
//    @Value("${spring.redis.timeout}")   //连接超时时间（毫秒）
//    private static int timeout;

    //初始化Redis连接池
//    static {
//        try{
//            JedisPoolConfig config = getRedisConfig();
//            jedisPool = new JedisPool(config, host, port,timeout, password);
//        }catch (Exception e){e.printStackTrace();}
//    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/6 10:29
     * @功能描述	注入配置文件中的redis连接池配置
     * @参数
     * @返回值
     *
     */
//    @Bean
//    @ConfigurationProperties(prefix = "spring.redis")
//    public static  JedisPoolConfig getRedisConfig()
//    {
//        JedisPoolConfig config = new JedisPoolConfig();
//        return config;
//    }


    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/6 12:21
     * @功能描述	获取redis实例
     * @参数
     * @返回值     Jedis实例
     *
     */
//    public synchronized static Jedis getJedis()
//    {
//        try{
//            if(jedisPool != null)
//            {
//                Jedis resource = jedisPool.getResource();
//                return resource;
//            }else{return null;}
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/6 12:25
     * @功能描述	释放jedis资源
     * @参数
     * @返回值
     *
     */
//    public static void close(final Jedis jedis)
//    {
//        if(jedis != null)
//        {
//            jedis.close();
//        }
//    }


    /**
     * 注入 RedisConnectionFactory
     */
    @Autowired
    RedisConnectionFactory redisConnectionFactory ;

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
    public RedisTemplate<String, Object> functionDomainRedisTemplate()
    {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 15:43
     * @功能描述	设置数据存入redis的序列化方式
     * @参数     RedisTemplate
     * @参数      factory
     * @返回值     空值
     *
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory)
    {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(factory);
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 15:51
     * @功能描述	实例化HashOperations对象，可以使用Hash类型操作
     * @参数  RedisTemplate
     * @返回值 HashOperations对象
     *
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate)
    {
        return redisTemplate.opsForHash();
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 15:54
     * @功能描述	实例化ValueOperations对象，可以使用String操作
     * @参数  redisTemplate
     * @返回值 ValueOperations
     *
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate)
    {
        return redisTemplate.opsForValue();
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 15:55
     * @功能描述	实例化ListOperations对象，可以使用List操作
     * @参数  redisTemplate
     * @返回值 ListOperations对象
     *
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate)
    {
        return redisTemplate.opsForList();
    }


    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 15:57
     * @功能描述	实例化SetOperations对象，可以使用Set操作
     * @参数  RedisTemplate
     * @返回值 SetOperations对象
     *
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate)
    {
        return redisTemplate.opsForSet();
    }

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/6/5 16:00
     * @功能描述	实例化ZSetOperations对象，可以使用ZSet操作
     * @参数  redisTemplate
     * @返回值 ZSetOperations对象
     *
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate)
    {
        return redisTemplate.opsForZSet();
    }



}
