package baloght.tongue.data.prefs;

import baloght.tongue.data.DataManager;

/**
 * Created by Balogh Tamas on 2018. 03. 19..
 */

public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);
}
