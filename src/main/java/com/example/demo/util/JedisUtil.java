package com.example.demo.util;

import com.example.demo.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Objects;

/**
 * Description:  jedis 工具类
 */
@Component
public class JedisUtil {

    private static JedisPool jedisPool;

    @Autowired
    public void setJedisPool(JedisPool jedisPool) {
        JedisUtil.jedisPool = jedisPool;
    }

    /**
     * 获取实例
     * @param
     * @return
     */
    public static Jedis getJedis(){

        if(null != jedisPool){
            Jedis resource = jedisPool.getResource();
            return resource;
        }else {
            return null;
        }
    }

    /**
     * String 类型  set
     * @param key
     * @param value
     * @return
     */
    public static String setValue(String key, Objects value,int expiretime){
        try(Jedis jedis = jedisPool.getResource()){
             String set = jedis.set(key.getBytes(), SerializationUtils.serialize(value));
             if(Constants.OK.equals(set)){
                 jedis.expire(key.getBytes(),expiretime);
             }
             return set;
        }
    }

    /**
     * String 类型  get
     * @param key
     * @return
     */
    public static String getValue(String key) {
        try(Jedis jedis = jedisPool.getResource()){
           return jedis.get(key);
        }
    }


    /**
     * 按key刪除
     *
     * @param key
     * @return
     */
    public static Long del(String... key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.del(key);
        }
    }

    /**
     * 检查key是否存在
     *
     * @param key
     * @return
     */
    public static Boolean exists(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key.getBytes());
        }
    }


    /**
     * 存储REDIS队列 顺序存储
     * @param  key reids键名
     * @param  value 键值
     */
    public static void lpush(String key, Object value) {

        try (Jedis jedis = jedisPool.getResource()){
            jedis.lpush(key.getBytes(),SerializationUtils.serialize(value) );
        }
    }

    /**
     * 存储REDIS队列 反向存储
     * @param  key reids键名
     * @param  value 键值
     */
    public static void rpush(String key, Object value) {

        try (Jedis jedis = jedisPool.getResource()){
            jedis.rpush(key.getBytes(),SerializationUtils.serialize(value));
        }
    }

    /**
     * 将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端
     * @param  key reids键名
     * @param  value 键值
     */
    public static void rpoplpush(String key, Object value) {

        try (Jedis jedis = jedisPool.getResource()){
            jedis.rpoplpush(key.getBytes(), SerializationUtils.serialize(value));
        }
    }

    /**
     * 获取队列数据
     * @param  key 键名
     * @return
     */
    public static List<byte[]> lpopList(String key) {

        try (Jedis jedis = jedisPool.getResource()){
            return jedis.lrange(key.getBytes(), 0, -1);
        }
    }

    /**
     * 获取队列数据
     * @param  key 键名
     * @return
     */
    public static byte[] rpop(String key) {

        try (Jedis jedis = jedisPool.getResource()){
            return  jedis.rpop(key.getBytes());
        }
    }

}
