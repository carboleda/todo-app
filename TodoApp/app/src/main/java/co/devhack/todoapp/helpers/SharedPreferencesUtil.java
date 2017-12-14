package co.devhack.todoapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by krlosf on 12/12/17.
 */

public class SharedPreferencesUtil {
    private static Context context;
    private static SharedPreferences sharedPreferences;

    public static void init(Context context) {
        SharedPreferencesUtil.context = context;
    }

    public static SharedPreferences getSharedPreferences() {
        if(sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(Constants.MY_SHARED_PREF, Context.MODE_PRIVATE);
            //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return sharedPreferences;
    }
}
