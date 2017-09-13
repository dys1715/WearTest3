package winsion.weartest3.model.support;

import java.util.WeakHashMap;

/**
 * Created by zhoucong on 2016/11/24.
 */

public class M {
    private static WeakHashMap<Class<?>, Object> singletonObjectPool = new WeakHashMap<>();

    @SuppressWarnings("unchecked")
    public static synchronized <T> T get(Class<T> clazz) {
        return get(clazz, ModelMode.SINGLETON);
    }

    public static synchronized <T> T get(Class<T> clazz, ModelMode mode) {
        if (mode == ModelMode.SINGLETON) {
            if (!singletonObjectPool.containsKey(clazz)) {
                try {
                    singletonObjectPool.put(clazz, clazz.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return (T) singletonObjectPool.get(clazz);
        } else if (mode == ModelMode.PROTOTYPE) {
            T inst = null;
            try {
                inst = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return inst;
        }
        return null;
    }
}
