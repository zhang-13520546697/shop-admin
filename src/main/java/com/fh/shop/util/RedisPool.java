package com.fh.shop.util;

import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <pre>包名称：com.fh.shop.util
 * 类名称：RedisPool
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/714:36
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class RedisPool {


    private static String host;
    private static int port;

    @Value("${redis.host}")
    public  void setHost(String host) {
        RedisPool.host = host;
    }
    @Value("${redis.port}")
    public  void setPort(int port) {
        RedisPool.port = port;
    }

    private RedisPool(){}
    private static  JedisPool jedisPool;

    private static void initPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //总的连接数
        jedisPoolConfig.setMaxTotal(1000);
        //空闲时最大的连接数
        jedisPoolConfig.setMaxIdle(100);
        //空闲时最小的连接数
        jedisPoolConfig.setMinIdle(100);
        //验证的连接的可用性
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPool = new JedisPool(jedisPoolConfig,"192.168.110.129",7020);
    }

    static {
        //类加载的时候执行静态语句块   而且只会执行一次
        initPool();
    }

    public static Jedis getResource(){
        return jedisPool.getResource();
    }



}
