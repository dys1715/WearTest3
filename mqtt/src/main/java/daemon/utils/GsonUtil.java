package daemon.utils;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhoucong on 2016/11/16.
 */

public class GsonUtil {
    private Gson gson = new Gson();

    public static Gson getGson() {
        return GsonUtilSingletonHolder.instance.gson;
    }

    public static <T> List<T> jsonToList(String json, Class<T[]> clazz) {
        T[] array = getGson().fromJson(json, clazz);
        return Arrays.asList(array);
    }

    private static class GsonUtilSingletonHolder {
        static GsonUtil instance = new GsonUtil();
    }
}
