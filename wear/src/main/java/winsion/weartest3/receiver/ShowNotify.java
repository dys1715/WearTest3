package winsion.weartest3.receiver;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.Gravity;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import daemon.bean.PushBean;
import winsion.weartest3.MainActivity;
import winsion.weartest3.R;

/**
 * Created by dys on 2017/9/8 0008.
 */
public class ShowNotify {

    public static void setWearNotify(Context context, String result) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        if (!result.contains("{")) {
            builder.setContentText(result);
        } else {
            PushBean pushBean = new Gson().fromJson(result, PushBean.class);
            // 1.设置显示内容
//        builder.setContentTitle("消息标题");
            String name = pushBean.getAlarm().getTargetDto().getTargetName();
            String type = "(" + pushBean.getAlarm().getTargetDto().getTargetBehaviour() + ")\n";
            String entrance = "从" + pushBean.getAlarm().getCheckRecord().getEntrance() + "进入";
            builder.setContentText(name + type + entrance);
        }

        // 若只设置了SmallIcon,而没设置LargeIcon,则在通知栏左侧会显示SmallIcon设置的图标;若同时设置了LargeIcon,则左侧显示LargeIcon,右侧显示SmallIcon
        builder.setSmallIcon(R.mipmap.ic_launcher);
        // 若设置了LargeIcon,则Wear背景会变成LargeIcon.
//         builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        // 2.设置跳转属性
        Intent intent = new Intent(context, MainActivity.class);
//        intent.putExtra("result", result);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        // 设置了ContentIntent后,通知栏有了点击效果,而wear滑动到最右侧时,多了一个Open on phone的页面
        builder.setContentIntent(pendingIntent);

        // 3.设置通知属性
        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_ALL);

        // 4.设置手表特有属性
        builder.extend(extendWear(context));

        //5.eventBus
        EventBus.getDefault().postSticky(result);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(1, builder.build());
    }

    private static NotificationCompat.WearableExtender extendWear(Context context) {

        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();

        //1.设置背景
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 官方文档提示 : http://developer.android.com/training/wearables/notifications/creating.html
        // 可滚动,背景则为640x400,否则为400x400.
        // 若设置了背景,则LargeIcon在Wear端失效
        options.outWidth = 640;
        options.outHeight = 400;
        Bitmap wearBgBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher, options);
        // 设置了Bg后,LargeIcon便失效
        wearableExtender.setBackground(wearBgBitmap);

        //2. 为Wear添加独有的action
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Action action = new NotificationCompat.Action(R.mipmap.ic_launcher, "查看详情", pendingIntent);
        wearableExtender.addAction(action);

        // 3. 调整首页的图标
        // 隐藏默认应用图标
        wearableExtender.setHintHideIcon(false);
        // 设置跟ContentText跟随的图标
//        wearableExtender.setContentIcon(R.mipmap.abm2);
        // 只支持Start和End标签,默认是End
        wearableExtender.setContentIconGravity(Gravity.START);

        // 4. 添加第二页卡片
//        Notification secondPageNotification = new NotificationCompat.Builder(this)
//                .setContentTitle("page 2")
//                .setContentText("现在显示的Wear通知内容,测试可以显示多少内容,试试看,哈哈哈.继续来一个")
//                .build();
//        wearableExtender.addPage(secondPageNotification);

        return wearableExtender;

    }
}
