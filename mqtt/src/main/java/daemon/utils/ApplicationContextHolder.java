package daemon.utils;

import android.content.Context;

/**
 * Created by dxx on 2017/1/3.
 */

public class ApplicationContextHolder {
    private static Context applicationContext = null;

    public static Context getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(Context applicationContext) {
        if (ApplicationContextHolder.applicationContext == null) {
            ApplicationContextHolder.applicationContext = applicationContext;
        }
    }
}
