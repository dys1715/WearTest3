package winsion.weartest3.presenter;

import android.widget.ImageView;

import java.io.File;

import winsion.weartest3.MainActivity;
import winsion.weartest3.api.support.Consumer;
import winsion.weartest3.model.IconModel;
import winsion.weartest3.model.support.M;

/**
 * Created by dys on 2017/9/12 0012.
 */
public class MainPresenter {
    private MainActivity mMainActivity;
    private IconModel mIconModel;

    public MainPresenter(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        mIconModel = M.get(IconModel.class);
    }

    /**
     * 获取图片
     *
     * @param fileUrl
     */
//    public void getIconFile(String fileUrl, final ImageView imageView) {
//        mIconModel.getIconFile(fileUrl, new Consumer<File>() {
//            @Override
//            public void call(File file) {
//
//            }
//
//        });
//    }
}
