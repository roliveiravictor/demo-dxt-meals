package stonetree.com.meals.utils;

import android.app.Activity;
import android.widget.Toast;

public class ToastUtils {

    public static void show(final Activity activity, final CharSequence toastMessage, final int lengthLong) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity.getApplicationContext(), toastMessage, lengthLong).show();
            }
        });
    }

}
