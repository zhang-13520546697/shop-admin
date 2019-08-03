package com.fh.shop.util;

import redis.clients.jedis.Jedis;

/**
 * <pre>包名称：com.fh.shop.util
 * 类名称：RedisUtil
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/718:39
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class RedisUtil {

    public static void set (String key ,String value){
        Jedis resource = null;
        try {
            resource = RedisPool.getResource();
            resource.set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
           throw new RuntimeException(e);
        } finally {
            if(null != resource){
                resource.close();
            }
        }
    }

    public static  boolean setExNx(String key ,String value, int seconds){
        Jedis resource = null;
        try {
            resource = RedisPool.getResource();
            String result = resource.set(key, value, "nx", "ex", seconds);
            if(result == null ){
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(null != resource){
                resource.close();
            }
        }
    }

    public static void expire (String key ,int second){
        Jedis resource = null;
        try {
            resource = RedisPool.getResource();
            resource.expire(key,second);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(null != resource){
                resource.close();
            }
        }
    }

    public static boolean  exists(String key){
        Jedis resource = null;
        try {
            resource = RedisPool.getResource();
            return resource.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(null != resource){
                resource.close();
            }
        }
    }

    public static void setex(String key ,String value,int second){
        Jedis resource = null;
        try {
            resource = RedisPool.getResource();
            resource.setex(key,second,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        } finally {
            if(resource != null){
                resource.close();
            }
        }
    }

    public static String get (String key){
        Jedis resource = null;
        String value = "";
        try {
            resource = RedisPool.getResource();
            value = resource.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(null != resource){
                resource.close();
            }
        }
        return value;
    }

    public static void delArr (String[] key){
        Jedis resource = null;
        try {
            resource = RedisPool.getResource();
            resource.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != resource){
                resource.close();
            }
        }
    }

    public static void del (String key){
        Jedis resource = null;
        try {
            resource = RedisPool.getResource();
            resource.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != resource){
                resource.close();
            }
        }
    }

}
