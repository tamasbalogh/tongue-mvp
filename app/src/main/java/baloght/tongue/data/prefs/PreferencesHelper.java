package baloght.tongue.data.prefs;

import baloght.tongue.data.DataManager;

/**
 * Created by Balogh Tamas on 2018. 03. 19..
 */

public interface PreferencesHelper {

    int getUserLoggedInMode();

    void setUserLoggedInMode(DataManager.LoggedInMode mode);

    String getUserName();

    void setUserName(String userName);

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getUserProfilePicUrl();

    void setUserProfilePicUrl(String profilePicUrl);

    String getStoredProfilePicPath();

    void setStoredProfilePicPath(String profilePicPath);

}
