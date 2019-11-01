package org.nuaa.wa.waelder.util.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Name: PhoneCacheFactory
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-28 20:41
 * @Version: 1.0
 */
public class PhoneCacheFactory {
    private static Cache<String, String> cache;

    private static final class Holder {
        private static final PhoneCacheFactory INSTANCE = new PhoneCacheFactory();
    }

    private PhoneCacheFactory() {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(15, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }

    public static PhoneCacheFactory getInstance() {
        return Holder.INSTANCE;
    }

    public void set(String phone, String code) {
        cache.put(phone, code);
    }

    public String get(String phone) {
        return cache.getIfPresent(phone);
    }
}
