package winsion.weartest3;

import android.app.AlertDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.WatchViewStub;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import daemon.bean.PushBean;
import daemon.service.WearService;
import daemon.utils.TimeUtils;
import winsion.weartest3.presenter.MainPresenter;

public class MainActivity extends WearableActivity {
    private static final String TAG = "Wear_MainActivity>>>";
    private static final String ICON_PREFIX = "http://172.16.6.29:9222/file/getFile?fileUrl=";
    private ImageView mImg1, mImg2;
    private TextView mTextView;
    private Button mButton;
    private ScrollView mScrollView;
    private String mResult;
    private Vibrator mVibrator;  //声明一个振动器对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //启动环境变化可用，防止超超时不操作自动退出应用
        setAmbientEnabled();
        //初始化振动
        mVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);

        //启动mqtt
        Intent grayIntent = new Intent(getApplicationContext(), WearService.class); //GrayService
        startService(grayIntent);
        //wear布局
        WatchViewStub mStub = findViewById(R.id.watch_view_stub);
        mStub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mImg1 = stub.findViewById(R.id.iv_img1);
                mImg2 = stub.findViewById(R.id.iv_img2);
                mTextView = stub.findViewById(R.id.text);
                mButton = stub.findViewById(R.id.btn_confirm);
                mScrollView = stub.findViewById(R.id.scrollview);

                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME));
                    }
                });
            }
        });
        //EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        //进入待机的情况，在一定时间内没有接受到其他指令。应用退出不会被执行
    }

    @Override
    public void onExitAmbient() {
        super.onExitAmbient();
        //再次收到指令，并且被唤醒，类似手机被唤醒。第一次进入应用的时候 该方法不被执行。
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        //每分钟更新一次,只要应用运行情况下每一分钟（就是手表分钟发生变化）触发。
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mScrollView != null) {
            mScrollView.scrollTo(0, 0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onResultEvent(String result) {
        Log.e(TAG, "--onResultEvent--\n" + result);
        if (hasWindowFocus()) {
            //填充信息
            setInfo(result);
        } else {
            mResult = result;
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e(TAG, "-----onWindowFocusChanged-----");
        //当activity获得焦点之后，activity是加载完毕的了
        if (hasFocus) {
            //填充信息
            setInfo(mResult);
            mVibrator.cancel();
        }
    }

    /**
     * 配置页面信息
     *
     * @param result
     */
    private void setInfo(String result) {
        if (TextUtils.isEmpty(result)) {
            Log.e(TAG, "push result is null");
            return;
        }
        if (!result.contains("{")) {
            mTextView.setText(result);
            return;
        }
        PushBean bean = new Gson().fromJson(result, PushBean.class);
        if (bean.getAlarm().getCheckRecord().getCaughtImageUrlList() != null
                && bean.getAlarm().getCheckRecord().getCaughtImageUrlList().size() > 0) {
            //caught头像
            initPersonIcon(mImg1,
                    ICON_PREFIX + bean.getAlarm().getCheckRecord().getCaughtImageUrlList().get(0));
        }
        if (bean.getAlarm().getTargetDto().getImageList() != null
                && bean.getAlarm().getTargetDto().getImageList().size() > 0) {
            //target头像
            initPersonIcon(mImg2,
                    ICON_PREFIX + bean.getAlarm().getTargetDto().getImageList().get(0));
        }
//        initPersonIcon(mImg1,
//                ICON_PREFIX + "E%3A%5Cdu%5C3-face%5CalarmSystem%5Calarm-images%5C2017-09-12%5" +
//                        "C0%5C0%5C0%5C1505184471385%5C1.jpg");
//        initPersonIcon(mImg2,
//                ICON_PREFIX + "E%3A%5Cdu%5C3-face%5CalarmSystem%5Calarm-images%5C2017-09-11%5" +
//                        "C0%5C0%5C0%5C11-20170831100713709cau001.jpg");
        String time = "时间：" + TimeUtils.formatDateOnlyHm(bean.getAlarm().getCheckRecord().getCheckTime()) + "\n";
        String name = "姓名：" + bean.getAlarm().getTargetDto().getTargetName() + "\n";
        final String idCode = "身份证号：\n" + bean.getAlarm().getCheckRecord().getIdcInfoDto().getIdcCode() + "\n";
        String type = "类型：" + bean.getAlarm().getTargetDto().getTargetBehaviour() + "\n";
        String similarity;
        if ("ImageFeature".equals(bean.getAlarm().getAlarmType())) {
            similarity = "相似度：" + bean.getAlarm().getSimilarity() + "%\n";
        } else {
            similarity = "相似度：0%\n";
        }
        final String entrance = "入口：" + bean.getAlarm().getCheckRecord().getEntrance() + "\n";
        final String trainInfo = "车次："
                + bean.getAlarm().getCheckRecord().getTravelInfoDto().getTrainNumber() + " "
                + bean.getAlarm().getCheckRecord().getTravelInfoDto().getCarriage() + "车"
                + bean.getAlarm().getCheckRecord().getTravelInfoDto().getSeatNumber() + "\n";
        mTextView.setText(time + name + idCode + type + similarity + entrance + trainInfo);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage(idCode)
                        .show();
            }
        });
    }

    /**
     * 加载图片
     * @param imageView
     * @param url
     */
    public void initPersonIcon(ImageView imageView, String url) {
        Glide.with(this)
                .load(url)
                .fitCenter()
                .placeholder(R.mipmap.ic_holder)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //写下你希望按下返回键达到的效果代码，不写则不会有反应
            startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME));
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
