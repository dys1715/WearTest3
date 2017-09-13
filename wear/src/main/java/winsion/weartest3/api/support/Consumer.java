package winsion.weartest3.api.support;

/**
 * Created by zhoucong on 2016/11/17.
 */

public interface Consumer<T> {
    void call(T t);
}
