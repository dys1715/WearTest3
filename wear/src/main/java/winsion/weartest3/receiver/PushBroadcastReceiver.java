package winsion.weartest3.receiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.util.Log;

import daemon.bean.PushBean;
import winsion.weartest3.R;

/**
 * Created by sun_rise on 2016/7/7.
 */

public class PushBroadcastReceiver extends BroadcastReceiver {
    private Vibrator mVibrator;  //声明一个振动器对象
    private SoundPool soundPool;

    public void onReceive(Context context, Intent intent) {

        setSoundAndVibrator(context);

        String result = intent.getStringExtra("result");
        Log.e("result in Receiver>>>", result);

        if (getResult(result) != null) {
            ShowNotify.setWearNotify(context, result);
            //四个参数就是——停止 开启 停止 开启
            //-1不重复，非-1为从pattern的指定下标开始重复
            //停止1秒，开启震动n秒，然后又停止1秒，又开启震动n秒，不重复.
            mVibrator.vibrate(new long[]{1000, 1000 * 2, 1000, 1000 * 2}, -1);
        }
    }

    /**
     * 设置声音和震动
     *
     * @param context
     */
    private void setSoundAndVibrator(Context context) {
        mVibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        //第一个参数为soundPool可以支持的声音数量，这决定了Android为其开设多大的缓冲区，
        //第二个参数为声音类型
        //第三个为声音品质，越高越耗资源
        soundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 5);
        //第三个参数为声音的优先级，当多个声音冲突而无法同时播放时，系统会优先播放优先级高的。
        soundPool.load(context, R.raw.super_mario, 1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                //第一个参数为id，id即为放入到soundPool中的顺序。
                //第二个和第三个参数为左右声道的音量控制。
                //第四个参数为优先级，由于只有这一个声音，因此优先级在这里并不重要。
                //第五个参数为是否循环播放，0为不循环，-1为循环,其他值为loop+1次。
                //最后一个参数为播放比率，从0.5到2，一般为1，表示正常播放。
                soundPool.play(1, 1, 1, 0, 0, 1);
            }
        });
    }

    private String getResult(String result) {
        try {
            if (!result.equals("Hello")) {
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
