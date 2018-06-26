package com.nwuer.core.common.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.nwuer.core.common.Const;
import com.nwuer.core.dto.UserDto;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author vividzc
 * @date 2018/6/16 20:10
 */
public class RegisterCache {
    private static int maxSize = 1000; //最大缓存数目
    private static LoadingCache<String,UserDto> localCache = CacheBuilder
            .newBuilder()
            .initialCapacity(50) //初始大小
            .maximumSize(maxSize) //最大缓存数目
            .expireAfterAccess(Const.REGISTER_OVER_TIME, TimeUnit.MINUTES)
            .build(new CacheLoader<String, UserDto>() {
                @Override
                public UserDto load(String s) throws Exception {
                    return null;
                }
            });

    /**
     * 将String存到Cache中
     * @param key
     * @param value
     */
    public static void setKey(String key,UserDto value){
        localCache.put(key,value);
    }

    /**
     * 从缓存中拿value
     * @param key
     * @return
     * @throws ExecutionException
     */
    public static UserDto getKey(String key) throws ExecutionException {
        UserDto value = localCache.get(key);
        return value;
    }
}
