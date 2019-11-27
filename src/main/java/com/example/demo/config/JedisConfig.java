package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Description: redis池配置类
 */
@Configuration
@EnableAutoConfiguration
@ConfigurationProperties
public class JedisConfig {

    @Value("${spring.redis.database}")
    private int database;
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.jedis.pool.max-active}")
    private int active;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private int wait;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean
    public JedisPool jedisPool(){

        JedisPoolConfig jedisPoolConfig =new JedisPoolConfig();
        //最大空闲连接数
        jedisPoolConfig.setMaxIdle(maxIdle);
        //最大阻塞等待时间
        jedisPoolConfig.setMaxWaitMillis(wait);
        //最小空闲连接数
        jedisPoolConfig.setMinIdle(minIdle);
        //最大连接数
        jedisPoolConfig.setMaxTotal(active);
        //jedis 第一次启动时，会报错
        jedisPoolConfig.setTestOnBorrow(false);

        jedisPoolConfig.setTestOnReturn(true);

        return new JedisPool(jedisPoolConfig, host, port, timeout, null, database);
    }
}
