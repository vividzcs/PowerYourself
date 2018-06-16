package com.nwuer.core.common.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author vividzc
 * @date 2018/6/16 20:10
 */
public class TokenCache {
    private static int expireTime = 24; //默认过期时间
    private static int maxSize = 1000; //最大缓存数目
    private static LoadingCache<String,String> localCache = CacheBuilder
            .newBuilder()
            .initialCapacity(50) //初始大小
            .maximumSize(maxSize) //最大缓存数目
            .expireAfterAccess(expireTime, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key,String value){
        localCache.put(key,value);
    }

    public static String getKey(String key) throws ExecutionException {
        String value = localCache.get(key);
        return "null".equals(value) ? null : value;
    }
}
