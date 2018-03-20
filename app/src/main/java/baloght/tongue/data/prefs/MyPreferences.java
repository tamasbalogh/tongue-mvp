package baloght.tongue.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Balogh Tamas on 2018. 03. 20..
 */

public class MyPreferences implements PreferencesHelper {

    public static final String PREF_NAME = "tongue_pref";

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private final SharedPreferences myPrefs;

    public MyPreferences(Context context, String prefFileName) {
        myPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return myPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        myPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getCurrentEmail() {
        return myPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }

    @Override
    public void setCurrentEmail(String email) {
        myPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, email).apply();
    }

    @Override
    public String getAccessToken() {
        return myPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        myPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }
}
