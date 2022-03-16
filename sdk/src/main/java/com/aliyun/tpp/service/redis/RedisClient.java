/**
 * author: oe date:   2021/8/30 comment: RedisClient
 */
package com.aliyun.tpp.service.redis;

import com.aliyun.tpp.service.inner.Service;
import redis.clients.jedis.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

//单例使用
public interface RedisClient extends Service<RedisConfig> {

    @Override
    default void setConfig(RedisConfig redisConfig) {

        setRedisConfig(redisConfig);
    }

    /**
     * 在init之前设置redisConfig
     */
    void setRedisConfig(RedisConfig redisConfig);

    /**
     * 初始化redis连接池
     */
    @PostConstruct
    void init();

    /**
     * when closing your application
     */
    @PreDestroy
    void destroy();

    /**
     * 获取redis连接
     */
    Jedis getResource();

    /**
     * 获取redis连接
     */
    void returnResource(Jedis redis);


    default Long del(byte[]... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.del(keys);
        } finally {
            returnResource(jedis);
        }
    }

    default List<byte[]> blpop(int timeout, byte[]... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.blpop(timeout, keys);
        } finally {
            returnResource(jedis);
        }
    }

    default List<byte[]> brpop(int timeout, byte[]... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.brpop(timeout, keys);
        } finally {
            returnResource(jedis);
        }
    }

    default List<byte[]> blpop(byte[]... args) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.blpop(args);
        } finally {
            returnResource(jedis);
        }
    }

    default List<byte[]> brpop(byte[]... args) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.brpop(args);
        } finally {
            returnResource(jedis);
        }
    }

    default Set<byte[]> keys(byte[] pattern) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.keys(pattern);
        } finally {
            returnResource(jedis);
        }
    }

    default List<byte[]> mget(byte[]... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.mget(keys);
        } finally {
            returnResource(jedis);
        }
    }

    default String mset(byte[]... keysvalues) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.mset(keysvalues);
        } finally {
            returnResource(jedis);
        }
    }

    default Long msetnx(byte[]... keysvalues) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.msetnx(keysvalues);
        } finally {
            returnResource(jedis);
        }
    }

    default String rename(byte[] oldKey, byte[] newKey) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.rename(oldKey, newKey);
        } finally {
            returnResource(jedis);
        }
    }

    default Long renamenx(byte[] oldKey, byte[] newKey) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.renamenx(oldKey, newKey);
        } finally {
            returnResource(jedis);
        }
    }

    default byte[] rpoplpush(byte[] srckey, byte[] dstKey) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.rpoplpush(srckey, dstKey);
        } finally {
            returnResource(jedis);
        }
    }

    default Set<byte[]> sdiff(byte[]... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sdiff(keys);
        } finally {
            returnResource(jedis);
        }
    }


    default Long sdiffstore(byte[] dstKey, byte[]... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sdiffstore(dstKey, keys);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> sinter(byte[]... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sinter(keys);
        } finally {
            returnResource(jedis);
        }
    }


    default Long sinterstore(byte[] dstKey, byte[]... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sinterstore(dstKey, keys);
        } finally {
            returnResource(jedis);
        }
    }


    default Long smove(byte[] srcKey, byte[] dstKey, byte[] member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.smove(srcKey, dstKey, member);
        } finally {
            returnResource(jedis);
        }
    }


    default Long sort(byte[] key, SortingParams sortingParams, byte[] dstKey) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sort(key, sortingParams, dstKey);
        } finally {
            returnResource(jedis);
        }
    }

    default Long sort(byte[] key, byte[] dstKey) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sort(key, dstKey);
        } finally {
            returnResource(jedis);
        }
    }

    default Set<byte[]> sunion(byte[]... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sunion(keys);
        } finally {
            returnResource(jedis);
        }
    }


    default Long sunionstore(byte[] dstKey, byte[]... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sunionstore(dstKey, keys);
        } finally {
            returnResource(jedis);
        }
    }


    default String watch(byte[]... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.watch(keys);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zinterstore(byte[] dstKey, byte[]... sets) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zinterstore(dstKey, sets);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zinterstore(byte[] dstKey, ZParams zParams, byte[]... sets) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zinterstore(dstKey, zParams, sets);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zunionstore(byte[] dstKey, byte[]... sets) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zunionstore(dstKey, sets);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zunionstore(byte[] dstKey, ZParams zParams, byte[]... sets) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zunionstore(dstKey, zParams, sets);
        } finally {
            returnResource(jedis);
        }
    }


    default byte[] brpoplpush(byte[] source, byte[] destination, int timeout) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.brpoplpush(source, destination, timeout);
        } finally {
            returnResource(jedis);
        }
    }


    default Long publish(byte[] channel, byte[] message) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.publish(channel, message);
        } finally {
            returnResource(jedis);
        }
    }


    default void subscribe(BinaryJedisPubSub jedisPubSub, byte[]... channels) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.subscribe(jedisPubSub, channels);
        } finally {
            returnResource(jedis);
        }
    }


    default void psubscribe(BinaryJedisPubSub jedisPubSub, byte[]... patterns) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.psubscribe(jedisPubSub, patterns);
        } finally {
            returnResource(jedis);
        }
    }


    default byte[] randomBinaryKey() {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.randomBinaryKey();
        } finally {
            returnResource(jedis);
        }
    }


    default Long bitop(BitOP op, byte[] destKey, byte[]... srcKeys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.bitop(op, destKey, srcKeys);
        } finally {
            returnResource(jedis);
        }
    }


    default String pfmerge(byte[] destKey, byte[]... srcKey) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.pfmerge(destKey, srcKey);
        } finally {
            returnResource(jedis);
        }
    }


    default Long pfcount(byte[]... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.pfcount(keys);
        } finally {
            returnResource(jedis);
        }
    }


    default Long del(String... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.del(keys);
        } finally {
            returnResource(jedis);
        }
    }


    default List<String> blpop(int timeout, String... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.blpop(timeout, keys);
        } finally {
            returnResource(jedis);
        }
    }


    default List<String> brpop(int timeout, String... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.brpop(timeout, keys);
        } finally {
            returnResource(jedis);
        }
    }


    default List<String> blpop(String... args) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.blpop(args);
        } finally {
            returnResource(jedis);
        }
    }


    default List<String> brpop(String... args) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.brpop(args);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> keys(String pattern) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.keys(pattern);
        } finally {
            returnResource(jedis);
        }
    }


    default List<String> mget(String... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.mget(keys);
        } finally {
            returnResource(jedis);
        }
    }


    default String mset(String... keysvalues) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.mset(keysvalues);
        } finally {
            returnResource(jedis);
        }
    }


    default Long msetnx(String... keysvalues) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.msetnx(keysvalues);
        } finally {
            returnResource(jedis);
        }
    }


    default String rename(String oldKey, String newKey) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.rename(oldKey, newKey);
        } finally {
            returnResource(jedis);
        }
    }


    default Long renamenx(String oldKey, String newKey) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.renamenx(oldKey, newKey);
        } finally {
            returnResource(jedis);
        }
    }


    default String rpoplpush(String srckey, String dstkey) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.rpoplpush(srckey, dstkey);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> sdiff(String... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sdiff(keys);
        } finally {
            returnResource(jedis);
        }
    }


    default Long sdiffstore(String dstKey, String... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sdiffstore(dstKey, keys);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> sinter(String... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sinter(keys);
        } finally {
            returnResource(jedis);
        }
    }


    default Long sinterstore(String dstKey, String... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sinterstore(dstKey, keys);
        } finally {
            returnResource(jedis);
        }
    }


    default Long smove(String srcKey, String dstKey, String member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.smove(srcKey, dstKey, member);
        } finally {
            returnResource(jedis);
        }
    }


    default Long sort(String key, SortingParams sortingParams, String dstKey) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sort(key, sortingParams, dstKey);
        } finally {
            returnResource(jedis);
        }
    }


    default Long sort(String key, String dstKey) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sort(key, dstKey);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> sunion(String... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sunion(keys);
        } finally {
            returnResource(jedis);
        }
    }


    default Long sunionstore(String dstkey, String... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sunionstore(dstkey, keys);
        } finally {
            returnResource(jedis);
        }
    }


    default String watch(String... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.watch(keys);
        } finally {
            returnResource(jedis);
        }
    }


    default String unwatch() {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.unwatch();
        } finally {
            returnResource(jedis);
        }
    }


    default Long zinterstore(String dstKey, String... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zinterstore(dstKey, keys);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zinterstore(String dstKey, ZParams zParams, String... sets) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zinterstore(dstKey, zParams, sets);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zunionstore(String dstKey, String... sets) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zunionstore(dstKey, sets);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zunionstore(String dstKey, ZParams zParams, String... sets) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zunionstore(dstKey, zParams, sets);
        } finally {
            returnResource(jedis);
        }
    }


    default String brpoplpush(String source, String destination, int timeout) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.brpoplpush(source, destination, timeout);
        } finally {
            returnResource(jedis);
        }
    }


    default Long publish(String channel, String message) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.publish(channel, message);
        } finally {
            returnResource(jedis);
        }
    }


    default void subscribe(JedisPubSub jedisPubSub, String... channels) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.subscribe(jedisPubSub, channels);
        } finally {
            returnResource(jedis);
        }
    }


    default void psubscribe(JedisPubSub jedisPubSub, String... patterns) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.psubscribe(jedisPubSub, patterns);
        } finally {
            returnResource(jedis);
        }
    }


    default String randomKey() {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.randomKey();
        } finally {
            returnResource(jedis);
        }
    }


    default Long bitop(BitOP op, String destKey, String... srcKeys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.bitop(op, destKey, srcKeys);
        } finally {
            returnResource(jedis);
        }
    }


    default ScanResult<String> scan(int cursor) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.scan(cursor);
        } finally {
            returnResource(jedis);
        }
    }


    default ScanResult<String> scan(String cursor) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.scan(cursor);
        } finally {
            returnResource(jedis);
        }
    }


    default String pfmerge(String dstKey, String... sourcekeys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.pfmerge(dstKey, sourcekeys);
        } finally {
            returnResource(jedis);
        }
    }


    default long pfcount(String... keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.pfcount(keys);
        } finally {
            returnResource(jedis);
        }
    }


    default String set(String key, String value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.set(key, value);
        } finally {
            returnResource(jedis);
        }
    }


    default String set(String key, String value, String nxxx, String expx, long time) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.set(key, value, nxxx, expx, time);
        } finally {
            returnResource(jedis);
        }
    }


    default String get(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.get(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Boolean exists(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.exists(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long persist(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.persist(key);
        } finally {
            returnResource(jedis);
        }
    }


    default String type(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.type(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long expire(String key, int seconds) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.expire(key, seconds);
        } finally {
            returnResource(jedis);
        }
    }


    default String set(byte[] key, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.set(key, value);
        } finally {
            returnResource(jedis);
        }
    }


    default String set(byte[] key, byte[] value, byte[] nxxx, byte[] expx, long time) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.set(key, value, nxxx, expx, time);
        } finally {
            returnResource(jedis);
        }
    }


    default byte[] get(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.get(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Boolean exists(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.exists(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long persist(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.persist(key);
        } finally {
            returnResource(jedis);
        }
    }


    default String type(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.type(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long expire(byte[] key, int seconds) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.expire(key, seconds);
        } finally {
            returnResource(jedis);
        }
    }


    default Long pexpire(String key, long milliseconds) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.pexpire(key, milliseconds);
        } finally {
            returnResource(jedis);
        }
    }


    default Long pexpire(byte[] key, long milliseconds) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.pexpire(key, milliseconds);
        } finally {
            returnResource(jedis);
        }
    }


    default Long expireAt(byte[] key, long unixTime) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.expireAt(key, unixTime);
        } finally {
            returnResource(jedis);
        }
    }


    default Long pexpireAt(byte[] key, long millisecondsTimestamp) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.pexpireAt(key, millisecondsTimestamp);
        } finally {
            returnResource(jedis);
        }
    }


    default Long ttl(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.ttl(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Boolean setbit(byte[] key, long offset, boolean value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.setbit(key, offset, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Boolean setbit(byte[] key, long offset, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.setbit(key, offset, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Boolean getbit(byte[] key, long offset) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.getbit(key, offset);
        } finally {
            returnResource(jedis);
        }
    }


    default Long setrange(byte[] key, long offset, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.setrange(key, offset, value);
        } finally {
            returnResource(jedis);
        }
    }


    default byte[] getrange(byte[] key, long startOffset, long endOffset) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.getrange(key, startOffset, endOffset);
        } finally {
            returnResource(jedis);
        }
    }


    default byte[] getSet(byte[] key, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.getSet(key, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Long setnx(byte[] key, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.setnx(key, value);
        } finally {
            returnResource(jedis);
        }
    }


    default String setex(byte[] key, int seconds, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.setex(key, seconds, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Long decrBy(byte[] key, long integer) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.decrBy(key, integer);
        } finally {
            returnResource(jedis);
        }
    }


    default Long decr(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.decr(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long incrBy(byte[] key, long integer) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.incrBy(key, integer);
        } finally {
            returnResource(jedis);
        }
    }


    default Double incrByFloat(byte[] key, double integer) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.incrByFloat(key, integer);
        } finally {
            returnResource(jedis);
        }
    }


    default Long incr(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.incr(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long append(byte[] key, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.append(key, value);
        } finally {
            returnResource(jedis);
        }
    }


    default byte[] substr(byte[] key, int start, int end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.substr(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Long hset(byte[] key, byte[] field, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hset(key, field, value);
        } finally {
            returnResource(jedis);
        }
    }


    default byte[] hget(byte[] key, byte[] field) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hget(key, field);
        } finally {
            returnResource(jedis);
        }
    }


    default Long hsetnx(byte[] key, byte[] field, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hsetnx(key, field, value);
        } finally {
            returnResource(jedis);
        }
    }


    default String hmset(byte[] key, Map<byte[], byte[]> hash) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hmset(key, hash);
        } finally {
            returnResource(jedis);
        }
    }


    default List<byte[]> hmget(byte[] key, byte[]... fields) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hmget(key, fields);
        } finally {
            returnResource(jedis);
        }
    }


    default Long hincrBy(byte[] key, byte[] field, long value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hincrBy(key, field, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Double hincrByFloat(byte[] key, byte[] field, double value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hincrByFloat(key, field, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Boolean hexists(byte[] key, byte[] field) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hexists(key, field);
        } finally {
            returnResource(jedis);
        }
    }


    default Long hdel(byte[] key, byte[]... fields) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hdel(key, fields);
        } finally {
            returnResource(jedis);
        }
    }


    default Long hlen(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hlen(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> hkeys(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hkeys(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Collection<byte[]> hvals(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hvals(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Map<byte[], byte[]> hgetAll(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hgetAll(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long rpush(byte[] key, byte[]... strings) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.rpush(key, strings);
        } finally {
            returnResource(jedis);
        }
    }


    default Long lpush(byte[] key, byte[]... strings) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lpush(key, strings);
        } finally {
            returnResource(jedis);
        }
    }


    default Long llen(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.llen(key);
        } finally {
            returnResource(jedis);
        }
    }


    default List<byte[]> lrange(byte[] key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lrange(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default String ltrim(byte[] key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.ltrim(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default byte[] lindex(byte[] key, long index) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lindex(key, index);
        } finally {
            returnResource(jedis);
        }
    }


    default String lset(byte[] key, long index, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lset(key, index, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Long lrem(byte[] key, long count, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lrem(key, count, value);
        } finally {
            returnResource(jedis);
        }
    }


    default byte[] lpop(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lpop(key);
        } finally {
            returnResource(jedis);
        }
    }


    default byte[] rpop(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.rpop(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long sadd(byte[] key, byte[]... members) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sadd(key, members);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> smembers(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.smembers(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long srem(byte[] key, byte[]... member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.srem(key, member);
        } finally {
            returnResource(jedis);
        }
    }


    default byte[] spop(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.spop(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> spop(byte[] key, long count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.spop(key, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Long scard(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.scard(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Boolean sismember(byte[] key, byte[] member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sismember(key, member);
        } finally {
            returnResource(jedis);
        }
    }


    default byte[] srandmember(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.srandmember(key);
        } finally {
            returnResource(jedis);
        }
    }


    default List<byte[]> srandmember(byte[] key, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.srandmember(key, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Long strlen(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.strlen(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zadd(byte[] key, double score, byte[] member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zadd(key, score, member);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zadd(byte[] key, Map<byte[], Double> scoreMembers) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zadd(key, scoreMembers);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> zrange(byte[] key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrange(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zrem(byte[] key, byte[]... members) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrem(key, members);
        } finally {
            returnResource(jedis);
        }
    }


    default Double zincrby(byte[] key, double score, byte[] member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zincrby(key, score, member);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zrank(byte[] key, byte[] member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrank(key, member);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zrevrank(byte[] key, byte[] member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrank(key, member);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> zrevrange(byte[] key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrange(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrangeWithScores(byte[] key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeWithScores(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrevrangeWithScores(byte[] key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeWithScores(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zcard(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zcard(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Double zscore(byte[] key, byte[] member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zscore(key, member);
        } finally {
            returnResource(jedis);
        }
    }


    default List<byte[]> sort(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sort(key);
        } finally {
            returnResource(jedis);
        }
    }


    default List<byte[]> sort(byte[] key, SortingParams sortingParams) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sort(key, sortingParams);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zcount(byte[] key, double min, double max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zcount(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zcount(byte[] key, byte[] min, byte[] max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zcount(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> zrangeByScore(byte[] key, double min, double max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScore(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScore(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScore(key, max, min);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScore(key, min, max, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScore(key, max, min);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScore(key, min, max, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScoreWithScores(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScoreWithScores(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zremrangeByRank(byte[] key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zremrangeByRank(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zremrangeByScore(byte[] key, double start, double end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zremrangeByScore(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zremrangeByScore(byte[] key, byte[] start, byte[] end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zremrangeByScore(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zlexcount(byte[] key, byte[] min, byte[] max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zlexcount(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByLex(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByLex(key, min, max, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByLex(key, max, min);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByLex(key, max, min, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zremrangeByLex(byte[] key, byte[] min, byte[] max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zremrangeByLex(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Long linsert(byte[] key, BinaryClient.LIST_POSITION where, byte[] pivot, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.linsert(key, where, pivot, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Long lpushx(byte[] key, byte[]... string) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lpushx(key, string);
        } finally {
            returnResource(jedis);
        }
    }


    default Long rpushx(byte[] key, byte[]... string) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.rpushx(key, string);
        } finally {
            returnResource(jedis);
        }
    }


    default List<byte[]> blpop(byte[] args) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.blpop(args);
        } finally {
            returnResource(jedis);
        }
    }


    default List<byte[]> brpop(byte[] args) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.brpop(args);
        } finally {
            returnResource(jedis);
        }
    }


    default Long del(byte[] keys) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.del(keys);
        } finally {
            returnResource(jedis);
        }
    }


    default byte[] echo(byte[] string) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.echo(string);
        } finally {
            returnResource(jedis);
        }
    }


    default Long move(byte[] key, int dbIndex) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.move(key, dbIndex);
        } finally {
            returnResource(jedis);
        }
    }


    default Long bitcount(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.bitcount(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long bitcount(byte[] key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.bitcount(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Long pfadd(byte[] key, byte[]... elements) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.pfadd(key, elements);
        } finally {
            returnResource(jedis);
        }
    }


    default long pfcount(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.pfcount(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long expireAt(String key, long unixTime) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.expireAt(key, unixTime);
        } finally {
            returnResource(jedis);
        }
    }


    default Long pexpireAt(String key, long milliSecondsTimeStamp) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.pexpireAt(key, milliSecondsTimeStamp);
        } finally {
            returnResource(jedis);
        }
    }


    default Long ttl(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.ttl(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Boolean setbit(String key, long offset, boolean value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.setbit(key, offset, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Boolean setbit(String key, long offset, String value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.setbit(key, offset, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Boolean getbit(String key, long offset) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.getbit(key, offset);
        } finally {
            returnResource(jedis);
        }
    }


    default Long setrange(String key, long offset, String value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.setrange(key, offset, value);
        } finally {
            returnResource(jedis);
        }
    }


    default String getrange(String key, long startOffset, long endOffset) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.getrange(key, startOffset, endOffset);
        } finally {
            returnResource(jedis);
        }
    }


    default String getSet(String key, String value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.getSet(key, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Long setnx(String key, String value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.setnx(key, value);
        } finally {
            returnResource(jedis);
        }
    }


    default String setex(String key, int seconds, String value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.setex(key, seconds, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Long decrBy(String key, long integer) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.decrBy(key, integer);
        } finally {
            returnResource(jedis);
        }
    }


    default Long decr(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.decr(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long incrBy(String key, long integer) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.incrBy(key, integer);
        } finally {
            returnResource(jedis);
        }
    }


    default Double incrByFloat(String key, double value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.incrByFloat(key, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Long incr(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.incr(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long append(String key, String value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.append(key, value);
        } finally {
            returnResource(jedis);
        }
    }


    default String substr(String key, int start, int end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.substr(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Long hset(String key, String field, String value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hset(key, field, value);
        } finally {
            returnResource(jedis);
        }
    }


    default String hget(String key, String field) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hget(key, field);
        } finally {
            returnResource(jedis);
        }
    }


    default Long hsetnx(String key, String field, String value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hsetnx(key, field, value);
        } finally {
            returnResource(jedis);
        }
    }


    default String hmset(String key, Map<String, String> hash) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hmset(key, hash);
        } finally {
            returnResource(jedis);
        }
    }


    default List<String> hmget(String key, String... fields) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hmget(key, fields);
        } finally {
            returnResource(jedis);
        }
    }


    default Long hincrBy(String key, String field, long value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hincrBy(key, field, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Boolean hexists(String key, String field) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hexists(key, field);
        } finally {
            returnResource(jedis);
        }
    }


    default Long hdel(String key, String... fields) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hdel(key, fields);
        } finally {
            returnResource(jedis);
        }
    }


    default Long hlen(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hlen(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> hkeys(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hkeys(key);
        } finally {
            returnResource(jedis);
        }
    }


    default List<String> hvals(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hvals(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Map<String, String> hgetAll(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hgetAll(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long rpush(String key, String... strings) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.rpush(key, strings);
        } finally {
            returnResource(jedis);
        }
    }


    default Long lpush(String key, String... strings) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lpush(key, strings);
        } finally {
            returnResource(jedis);
        }
    }


    default Long llen(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.llen(key);
        } finally {
            returnResource(jedis);
        }
    }


    default List<String> lrange(String key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lrange(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default String ltrim(String key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.ltrim(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default String lindex(String key, long index) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lindex(key, index);
        } finally {
            returnResource(jedis);
        }
    }


    default String lset(String key, long index, String value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lset(key, index, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Long lrem(String key, long count, String value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lrem(key, count, value);
        } finally {
            returnResource(jedis);
        }
    }


    default String lpop(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lpop(key);
        } finally {
            returnResource(jedis);
        }
    }


    default String rpop(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.rpop(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long sadd(String key, String... members) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sadd(key, members);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> smembers(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.smembers(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long srem(String key, String... members) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.srem(key, members);
        } finally {
            returnResource(jedis);
        }
    }


    default String spop(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.spop(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> spop(String key, long count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.spop(key, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Long scard(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.scard(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Boolean sismember(String key, String member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sismember(key, member);
        } finally {
            returnResource(jedis);
        }
    }


    default String srandmember(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.srandmember(key);
        } finally {
            returnResource(jedis);
        }
    }


    default List<String> srandmember(String key, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.srandmember(key, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Long strlen(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.strlen(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zadd(String key, double score, String member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zadd(key, score, member);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zadd(String key, Map<String, Double> scoreMembers) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zadd(key, scoreMembers);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> zrange(String key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrange(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zrem(String key, String... members) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrem(key, members);
        } finally {
            returnResource(jedis);
        }
    }


    default Double zincrby(String key, double score, String member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zincrby(key, score, member);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zrank(String key, String member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrank(key, member);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zrevrank(String key, String member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrank(key, member);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> zrevrange(String key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrange(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrangeWithScores(String key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeWithScores(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrevrangeWithScores(String key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeWithScores(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zcard(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zcard(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Double zscore(String key, String member) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zscore(key, member);
        } finally {
            returnResource(jedis);
        }
    }


    default List<String> sort(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sort(key);
        } finally {
            returnResource(jedis);
        }
    }


    default List<String> sort(String key, SortingParams sortingParams) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sort(key, sortingParams);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zcount(String key, double min, double max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zcount(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zcount(String key, String min, String max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zcount(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> zrangeByScore(String key, double min, double max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScore(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> zrangeByScore(String key, String min, String max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScore(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> zrevrangeByScore(String key, double max, double min) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScore(key, max, min);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScore(key, min, max, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> zrevrangeByScore(String key, String max, String min) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScore(key, max, min);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScore(key, min, max, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> zrevrangeByScore(String key, double min, double max, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScoreWithScores(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScoreWithScores(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zremrangeByRank(String key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zremrangeByRank(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zremrangeByScore(String key, double start, double end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zremrangeByScore(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zremrangeByScore(String key, String start, String end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zremrangeByScore(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zlexcount(String key, String min, String max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zlexcount(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> zrangeByLex(String key, String min, String max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByLex(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrangeByLex(key, min, max, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> zrevrangeByLex(String key, String max, String min) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByLex(key, max, min);
        } finally {
            returnResource(jedis);
        }
    }


    default Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zrevrangeByLex(key, max, min, offset, count);
        } finally {
            returnResource(jedis);
        }
    }


    default Long zremrangeByLex(String key, String min, String max) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zremrangeByLex(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }


    default Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.linsert(key, where, pivot, value);
        } finally {
            returnResource(jedis);
        }
    }


    default Long lpushx(String key, String... strings) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lpushx(key, strings);
        } finally {
            returnResource(jedis);
        }
    }


    default Long rpushx(String key, String... strings) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.rpushx(key, strings);
        } finally {
            returnResource(jedis);
        }
    }

    @Deprecated
    default List<String> blpop(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.blpop(key);
        } finally {
            returnResource(jedis);
        }
    }


    default List<String> blpop(int timeout, String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.blpop(timeout, key);
        } finally {
            returnResource(jedis);
        }
    }


    @Deprecated
    default List<String> brpop(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.brpop(key);
        } finally {
            returnResource(jedis);
        }
    }


    default List<String> brpop(int timeout, String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.brpop(timeout, key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long del(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.del(key);
        } finally {
            returnResource(jedis);
        }
    }


    default String echo(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.echo(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long move(String key, int dbIndex) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.move(key, dbIndex);
        } finally {
            returnResource(jedis);
        }
    }


    default Long bitcount(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.bitcount(key);
        } finally {
            returnResource(jedis);
        }
    }


    default Long bitcount(String key, long start, long end) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.bitcount(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }

    @Deprecated
    default ScanResult<Map.Entry<String, String>> hscan(String key, int cursor) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hscan(key, cursor);
        } finally {
            returnResource(jedis);
        }
    }

    @Deprecated
    default ScanResult<String> sscan(String key, int cursor) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sscan(key, cursor);
        } finally {
            returnResource(jedis);
        }
    }

    @Deprecated
    default ScanResult<Tuple> zscan(String key, int cursor) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zscan(key, cursor);
        } finally {
            returnResource(jedis);
        }
    }


    default ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hscan(key, cursor);
        } finally {
            returnResource(jedis);
        }
    }


    default ScanResult<String> sscan(String key, String cursor) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.sscan(key, cursor);
        } finally {
            returnResource(jedis);
        }
    }


    default ScanResult<Tuple> zscan(String key, String cursor) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.zscan(key, cursor);
        } finally {
            returnResource(jedis);
        }
    }


    default Long pfadd(String key, String... elements) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.pfadd(key, elements);
        } finally {
            returnResource(jedis);
        }
    }


    default long pfcount(String key) {

        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.pfcount(key);
        } finally {
            returnResource(jedis);
        }
    }
}
