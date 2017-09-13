package winsion.weartest3.model;

import android.util.Log;

import java.io.File;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import winsion.weartest3.api.IconApi;
import winsion.weartest3.api.support.Consumer;
import winsion.weartest3.api.support.StringConverterFactory;
import winsion.weartest3.model.support.BaseModel;

/**
 * Created by dys on 2017/9/12 0012.
 */
public class IconModel extends BaseModel {
    private String TAG = "IconModel>>>";
    private IconApi mIconApi;

    public IconModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.6.29:9222")
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mIconApi = retrofit.create(IconApi.class);
    }

    /**
     * 获取图片
     *
     * @param fileUrl
     * @param consumer
     */
//    public void getIconFile(String fileUrl, final Consumer<File> consumer) {
//        mIconApi.getFile(fileUrl)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<File>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG,e.toString());
//                    }
//
//                    @Override
//                    public void onNext(File file) {
//                        Log.e(TAG,file.toString());
//                        consumer.call(file);
//                    }
//                });
//    }
}
