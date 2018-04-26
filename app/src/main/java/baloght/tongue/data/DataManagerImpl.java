package baloght.tongue.data;

import android.content.Context;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import javax.inject.Inject;
import javax.inject.Singleton;
import baloght.tongue.data.network.ApiHelperImpl;
import baloght.tongue.data.prefs.PreferencesHelperImpl;
import baloght.tongue.di.ApplicationContext;

/**
 * Created by Balogh Tamas on 2018. 04. 01..
 */

@Singleton
public class DataManagerImpl implements DataManager {

    private final Context context;
    private final PreferencesHelperImpl preferencesHelperImpl;
    private final ApiHelperImpl apiHelperImpl;

    @Inject
    public DataManagerImpl(@ApplicationContext Context context,
                           PreferencesHelperImpl preferencesHelperImpl,
                           ApiHelperImpl apiHelper) {
        this.context = context;
        this.preferencesHelperImpl = preferencesHelperImpl;
        this.apiHelperImpl = apiHelper;
    }


    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null,
                null);
    }

    @Override
    public void updateUserInfo(String accessToken, LoggedInMode loggedInMode,String usernName, String profilePicUrl, String storedProfilePicPath) {
        setAccessToken(accessToken);
        setUserName(usernName);
        setUserLoggedInMode(loggedInMode);
        setUserProfilePicUrl(profilePicUrl);
        setStoredProfilePicPath(storedProfilePicPath);
    }

    @Override
    public int getUserLoggedInMode() {
        return preferencesHelperImpl.getUserLoggedInMode();
    }

    @Override
    public void setUserLoggedInMode(LoggedInMode mode) {
        preferencesHelperImpl.setUserLoggedInMode(mode);
    }

    @Override
    public String getUserName() {
        return preferencesHelperImpl.getUserName();
    }

    @Override
    public void setUserName(String userName) {
        preferencesHelperImpl.setUserName(userName);
    }

    @Override
    public String getAccessToken() {
        return preferencesHelperImpl.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        preferencesHelperImpl.setAccessToken(accessToken);
    }

    @Override
    public String getUserProfilePicUrl() {
        return preferencesHelperImpl.getUserProfilePicUrl();
    }

    @Override
    public void setUserProfilePicUrl(String profilePicUrl) {
        preferencesHelperImpl.setUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public String getStoredProfilePicPath() {
        return preferencesHelperImpl.getStoredProfilePicPath();
    }

    @Override
    public void setStoredProfilePicPath(String profilePicPath) {
        preferencesHelperImpl.setStoredProfilePicPath(profilePicPath);
    }

    @Override
    public void doRegister(RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        apiHelperImpl.doRegister(requestParams,asyncHttpResponseHandler);
    }

    @Override
    public void doLoginWithServer(RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        apiHelperImpl.doLoginWithServer(requestParams, asyncHttpResponseHandler);
    }

    @Override
    public void doLoginWithFacebook(LoginButton loginButton, CallbackManager callbackManager, FacebookCallback<LoginResult> callback) {
        apiHelperImpl.doLoginWithFacebook(loginButton,callbackManager,callback);
    }

    @Override
    public void getUserData(String token, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        apiHelperImpl.getUserData(token,asyncHttpResponseHandler);
    }


}
