package winsion.weartest3;

import android.app.Application;

import daemon.utils.ApplicationContextHolder;

/**
 * Created by dys on 2017/9/12 0012.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationContextHolder.setApplicationContext(this);
    }
}
