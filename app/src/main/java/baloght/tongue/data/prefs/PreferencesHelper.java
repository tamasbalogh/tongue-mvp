package baloght.tongue.data.prefs;

/**
 * Created by Balogh Tamas on 2018. 03. 19..
 */

public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(LoggedInMode mode);

    String getCurrentEmail();

    void setCurrentEmail(String email);

    String getAccessToken();

    void setAccessToken(String accessToken);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_SERVER(1),
        LOGGED_IN_MODE_FB(2);

        private final int Type;

        LoggedInMode(int type) {
            Type = type;
        }

        public int getType() {
            return Type;
        }
    }
}
