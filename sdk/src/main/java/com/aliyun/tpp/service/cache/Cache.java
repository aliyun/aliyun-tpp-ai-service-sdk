/**
 * author: oe
 * date:   2021/8/31
 * comment:
 */
package com.aliyun.tpp.service.cache;


import java.io.Serializable;

public interface Cache<V extends Serializable> {

    /**
     * 写入缓存
     * @param ttl 缓存失效时间
     */
    boolean put(String key, V value, long ttl);

    //读取缓存
    V get(String key, V defaultValue);

    /**
     * 初始化
     */
    void init();

    /**
     * 清理缓存
     */
    void clean();

    /**
     * 清理缓存
     */
    void close();

    /**
     * 生成缓存 Key
     */
    static String buildKey(String separator,String ... keys ){
        StringBuilder sb  =new StringBuilder();
        for (String key:keys){
            sb.append(key).append(separator);
        }
        sb.deleteCharAt(sb.lastIndexOf(separator));
        return sb.toString();
    }

}
