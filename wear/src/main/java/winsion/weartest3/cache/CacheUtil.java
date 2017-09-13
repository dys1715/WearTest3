package winsion.weartest3.cache;

import daemon.utils.UIUtil;

/**
 * Created by zhoucong on 2016/11/17.
 */

public class CacheUtil {
    private ACache mCache = ACache.get(UIUtil.getContext());

    public static ACache getCache() {
        return CacheUtilSingletonHolder.instance.mCache;
    }

    private static class CacheUtilSingletonHolder {
        static CacheUtil instance = new CacheUtil();
    }
}
