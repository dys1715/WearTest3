package daemon;

import android.content.Context;
import android.content.Intent;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import java.util.Timer;
import java.util.TimerTask;

import daemon.utils.MQTTUtils;


/**
 * Created by sun_rise on 2016/7/1.
 */

public class Presenter {

    private int failureCount;
    private int span;
    private Context context;
    public Presenter(Context context){
        this.context = context;
    }

    public void test(){
        try {
            MQTT mqtt = MQTTUtils.getMqtt();

            //使用回调式API
            final CallbackConnection callbackConnection = mqtt.callbackConnection();

            //连接监听
            callbackConnection.listener(new MyListener(callbackConnection));


            //连接
            callbackConnection.connect(new  Callback<Void>() {
                //连接失败
                public void onFailure(Throwable value) {
                    System.out.println("============连接失败：" + value.getLocalizedMessage() + "============");
                    reConnect();
                }

                // 连接成功
                public void onSuccess(Void v) {
                    //订阅主题
                    Topic[] topics = {
                            new Topic("independence/alarm/topic", QoS.AT_MOST_ONCE)
                    };
                    callbackConnection.subscribe(topics, new Callback<byte[]>() {
                        //订阅主题成功
                        public void onSuccess(byte[] qoses) {
                            System.out.println("========订阅成功=======");
                        }

                        //订阅主题失败
                        public void onFailure(Throwable value) {
                            System.out.println("========订阅失败=======");
                            callbackConnection.disconnect(null);
                        }
                    });


//                    //发布消息
//                    callbackConnection.publish("foo", ("Hello").getBytes(), QoS.AT_MOST_ONCE, true, new Callback<Void>() {
//                        public void onSuccess(Void v) {
//                            System.out.println("===========消息发布成功============");
//                        }
//
//                        public void onFailure(Throwable value) {
//                            System.out.println("========消息发布失败=======");
//                        }
//                    });
                    span = MQTTUtils.INIT_SPAN;
                    failureCount = MQTTUtils.INIT_COUNT;
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reConnect() {
        if (failureCount ==  MQTTUtils.INIT_COUNT) {
            test();
        } else {
            if(span < MQTTUtils.MAX_SPAN){
                span = (failureCount / MQTTUtils.ADD_SPAN + 1) * 10 ;
            }

            TimerTask task = new TimerTask() {
                public void run() {
                    test();
                }
            };

            Timer timer = new Timer(true);
            timer.schedule(task, span* 1000); //延时1000ms后执行，1000ms执行一次
        }
        failureCount++;

    }

    class MyListener implements Listener {
        private CallbackConnection callbackConnection;
        public MyListener(CallbackConnection callbackConnection) {
            this.callbackConnection = callbackConnection;
        }

        //接收订阅话题发布的消息
        @Override
        public void onPublish(UTF8Buffer topic, Buffer payload, Runnable onComplete) {
            System.out.println("=============receive msg================" + new String(payload.toByteArray()));
            Intent i = new Intent("com.hmkcode.android.USER_ACTION");
            String result = new String(payload.toByteArray());
            i.putExtra("result",result);
            context.sendBroadcast(i);
            onComplete.run();
        }



        //连接失败
        @Override
        public void onFailure(Throwable value) {
            System.out.println("===========connect failure===========");
            callbackConnection.disconnect(null);
            reConnect();
        }

        //连接断开
        @Override
        public void onDisconnected() {
            System.out.println("====mqtt disconnected=====");

        }

        //连接成功
        @Override
        public void onConnected() {
            System.out.println("====mqtt connected=====");

        }
    }
}
