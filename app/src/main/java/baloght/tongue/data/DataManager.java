package baloght.tongue.data;
import baloght.tongue.data.network.ApiHelper;
import baloght.tongue.data.prefs.PreferencesHelper;

/**
 * Created by Balogh Tamas on 2018. 04. 01..
 */

public interface DataManager extends PreferencesHelper, ApiHelper{

    void setUserAsLoggedOut()
            ;

    void updateUserInfo(
            String accessToken,
            LoggedInMode loggedInMode,
            String email);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_SERVER(1),
        LOGGED_IN_MODE_FB(2);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
