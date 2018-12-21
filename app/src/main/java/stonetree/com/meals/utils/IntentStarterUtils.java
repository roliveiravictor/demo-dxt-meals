package stonetree.com.meals.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public final class IntentStarterUtils {

    public static void goFromTo(Activity activity, Class clazz) {
        Intent intent = new Intent(activity.getApplicationContext(), clazz);
        activity.startActivity(intent);
    }

    public static void goFromToWithClearedHistory(Activity activity, Class clazz) {
        Intent intent = new Intent(activity.getApplicationContext(), clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public static void goFromWithExtraBundleTo(Activity activity, Class clazz, Bundle bundle) {
        Intent intent = new Intent(activity.getApplicationContext(), clazz);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public static void goFromActivityToUrl(Activity activity, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        activity.startActivity(i);
    }
}


