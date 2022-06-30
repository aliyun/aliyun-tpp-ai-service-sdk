/**
 * author: oe
 * date:   2022/2/23
 * comment:
 */
package com.aliyun.tpp.service.testing;

import com.aliyun.tpp.service.redis.RedisClient;
import com.aliyun.tpp.service.redis.RedisConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.atomic.AtomicBoolean;

public class RedisClientForTesting implements RedisClient {
    //REDIS各项配置
    private RedisConfig redisConfig;
    //REDIS连接池
    private transient JedisPool jedisPool;
    //初始化标识
    private transient AtomicBoolean initial = new AtomicBoolean(false);

    /**
     * 构造函数
     **/
    public RedisClientForTesting() {
    }

    @Override
    public void setRedisConfig(RedisConfig redisConfig) {
        if (redisConfig.isValid()) {
            this.redisConfig = redisConfig;
        } else {
            throw new IllegalArgumentException("redisConfig is invalid");
        }
    }

    //初始化redis连接池
    @PostConstruct
    public  void init() {
        if (initial.compareAndSet(false, true)) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(redisConfig.getMaxIdleConnectionCount());
            config.setMaxTotal(redisConfig.getMaxTotalConnectionCount());
            config.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
            config.setTestOnBorrow(false);
            config.setTestOnReturn(false);
            this.jedisPool = new JedisPool(config,
                            redisConfig.getHost(),
                            redisConfig.getPort(),
                            redisConfig.getTimeout(),
                            redisConfig.getPassword(),
                            redisConfig.getDbIndex());
        }
    }

    //when closing your application
    @PreDestroy
    public synchronized void destroy() {
        if (jedisPool != null && !jedisPool.isClosed()) {
            jedisPool.destroy();
        }
        this.initial.compareAndSet(true, false);
    }

    //获取redis连接
    public synchronized Jedis getResource() {
        if (jedisPool == null) {
            throw new IllegalArgumentException("jedisPool is null");
        }
        if (jedisPool.isClosed()){
            throw new IllegalArgumentException("jedisPool is closed");
        }
        return this.jedisPool.getResource();
    }

    //获取redis连接
    public synchronized void returnResource(Jedis redis) {
        if (redis != null) {
            redis.close();
        }
    }
}
