package winsion.weartest3.model.support;

import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;

import daemon.utils.GsonUtil;
import winsion.weartest3.cache.ACache;
import winsion.weartest3.cache.CacheUtil;


/**
 * Created by zhoucong on 2016/11/24.
 */

public class BaseModel {
    protected ACache mCache = CacheUtil.getCache();

    protected void toCache(String key, Object value) {
        if (value instanceof String) {
            mCache.put(key, (String) value);
        } else {
            mCache.put(key, GsonUtil.getGson().toJson(value));
        }
    }

    protected void toCache(String key, Object value, int ttl) {
        if (value instanceof String) {
            mCache.put(key, (String) value, ttl);
        } else {
            mCache.put(key, GsonUtil.getGson().toJson(value), ttl);
        }
    }

    protected <T> T fromCache(String key, Class<T> clazz) {
        String json = mCache.getAsString(key);
        if (!TextUtils.isEmpty(json)) {
            return GsonUtil.getGson().fromJson(json, clazz);
        }
        return null;
    }

    protected String fromCache(String key) {
        return mCache.getAsString(key);
    }

    protected <T> List<T> fromCacheList(String key, Class<T[]> clazz) {
        String json = mCache.getAsString(key);
        if (!TextUtils.isEmpty(json)) {
            return Arrays.asList(GsonUtil.getGson().fromJson(json, clazz));
        }
        return null;
    }

    protected boolean deleteCache(String key) {
        return mCache.remove(key);
    }
}
