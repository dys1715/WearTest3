package winsion.weartest3.api.support;

import android.app.ProgressDialog;
import android.content.Context;

import daemon.utils.LogUtil;
import daemon.utils.ToastUtil;
import daemon.utils.UIUtil;
import rx.Observer;

/**
 * Created by zhoucong on 2016/11/16.
 */

public abstract class GsonApiResultHandler<T> implements Observer<ResponseData<T>> {
    private Context context;
    private ProgressDialog progressDialog;

    public GsonApiResultHandler(Context context) {
        this.context = context;
    }

    public GsonApiResultHandler() {
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
        LogUtil.e(e.getMessage());
        LogUtil.e(e.toString());
        e.printStackTrace();
    }

    @Override
    public void onNext(ResponseData<T> responseData) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        //拿到数据，解析数据response
        if (responseData != null) {
            int code = responseData.getCode();
            if (code == 0) {
                onSuccess(responseData);
            } else {
                onCodeError(responseData);
            }
        }
    }

    public abstract void onSuccess(ResponseData<T> responseData);

    /**
     * code值非0时，对外提供虚方法做处理。可以重写该方法，进行网络处理
     */
    public void onCodeError(ResponseData responseData) {
        int code = responseData.getCode();
        if (code == 500 || code == 400) {
            ToastUtil.shortNormal(UIUtil.getContext(), "网络断开");
        } else if (code == 220) {
            //连接超时
        } else {
            ToastUtil.shortNormal(UIUtil.getContext(), responseData.getMessage());
        }
    }
}
