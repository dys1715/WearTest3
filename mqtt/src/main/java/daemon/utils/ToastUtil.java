package daemon.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zhoucong on 2016/11/16.
 */

public class ToastUtil {
    private static Toast mToast;

    public static void shortNormal(Context context, CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
