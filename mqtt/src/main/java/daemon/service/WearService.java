package daemon.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import daemon.Presenter;

/**
 * Created by dys on 2017/9/12 0012.
 */
public class WearService extends Service {
    private final static String TAG = "WearService>>>";

    private Presenter presenter = new Presenter(this);

    @Override
    public void onCreate() {
        Log.e(TAG, "------onCreate------");
        presenter.test();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "------onDestroy------");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "------onStartCommand------");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
