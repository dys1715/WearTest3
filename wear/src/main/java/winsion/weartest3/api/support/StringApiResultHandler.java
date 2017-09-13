package winsion.weartest3.api.support;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import daemon.utils.GsonUtil;
import daemon.utils.LogUtil;
import daemon.utils.ToastUtil;
import daemon.utils.UIUtil;
import rx.Observer;

/**
 * Created by zhoucong on 2016/11/16.
 */

public abstract class StringApiResultHandler implements Observer<String> {
    private Context context;
    private ProgressDialog progressDialog;

    public StringApiResultHandler(Context context) {
        this.context = context;
    }

    public StringApiResultHandler() {
    }

    public void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    @Override
    public void onCompleted() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        ToastUtil.shortNormal(UIUtil.getContext(), "网络断开,请稍后再试");
        LogUtil.e(e.getMessage()+"");
        LogUtil.e(e.toString());
        e.printStackTrace();
    }

    @Override
    public void onNext(String responseString) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        Type defType = new TypeToken<ResponseData<Object>>() {
        }.getType();
        ResponseData<Object> responseData = GsonUtil.getGson().fromJson(responseString, defType);

        //拿到数据，解析数据response
        if (responseData != null) {
            int code = responseData.getCode();
            if (code == 0) {
                onSuccess(responseString);
            } else {
                onCodeError(responseData);
            }
        }
    }

    public abstract void onSuccess(String responseData);

    /**
     * code值非0时，对外提供虚方法做处理。可以重写该方法，进行网络处理
     */
    public void onCodeError(ResponseData responseData) {
        int code = responseData.getCode();
        if (code == 500) {
            ToastUtil.shortNormal(UIUtil.getContext(), "网络断开");
        } else {
            ToastUtil.shortNormal(UIUtil.getContext(), responseData.getMessage());
        }
    }
}
