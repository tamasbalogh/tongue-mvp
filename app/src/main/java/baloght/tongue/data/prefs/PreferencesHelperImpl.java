package baloght.tongue.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import baloght.tongue.data.DataManager;
import baloght.tongue.di.ApplicationContext;
import baloght.tongue.di.PreferenceName;

/**
 * Created by Balogh Tamas on 2018. 03. 20..
 */

@Singleton
public class PreferencesHelperImpl implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_CURRENT_USERNAME = "PREF_KEY_USERNAME";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_PROFILE_PIC_URL = "PREF_KEY_PROFILE_PIC";

    private final SharedPreferences myPrefs;

    @Inject
    public PreferencesHelperImpl(@ApplicationContext Context context,
                                 @PreferenceName String prefFileName) {
        myPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return myPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        myPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getCurrentUserName() {
        return myPrefs.getString(PREF_KEY_CURRENT_USERNAME,null);

    }

    @Override
    public void setCurrentUserName(String userName) {
        myPrefs.edit().putString(PREF_KEY_CURRENT_USERNAME,userName).apply();
    }

    @Override
    public String getAccessToken() {
        return myPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        myPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return myPrefs.getString(PREF_KEY_PROFILE_PIC_URL,null);
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        myPrefs.edit().putString(PREF_KEY_PROFILE_PIC_URL, profilePicUrl).apply();
    }
}
