package baloght.tongue.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import javax.inject.Inject;
import javax.inject.Singleton;

import baloght.tongue.data.image.ImageHandler;
import baloght.tongue.data.image.ImageHandlerImpl;
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
    private final ImageHandlerImpl imageHandler;

    @Inject
    public DataManagerImpl(@ApplicationContext Context context,
                           PreferencesHelperImpl preferencesHelperImpl,
                           ApiHelperImpl apiHelper,
                           ImageHandlerImpl imageHandler) {
        this.context = context;
        this.preferencesHelperImpl = preferencesHelperImpl;
        this.apiHelperImpl = apiHelper;
        this.imageHandler = imageHandler;
    }


    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null);
    }

    @Override
    public void updateUserInfo(String accessToken, LoggedInMode loggedInMode,String usernName, String profilePicUrl) {
        setAccessToken(accessToken);
        setCurrentUserName(usernName);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return preferencesHelperImpl.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        preferencesHelperImpl.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public String getCurrentUserName() {
        return preferencesHelperImpl.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        preferencesHelperImpl.setCurrentUserName(userName);
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
    public String getCurrentUserProfilePicUrl() {
        return preferencesHelperImpl.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        preferencesHelperImpl.setCurrentUserProfilePicUrl(profilePicUrl
        );
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

    @Override
    public String saveProfilePic(Bitmap bitmap) {
        return imageHandler.saveProfilePic(bitmap);
    }

    @Override
    public Bitmap downloadBitmap(String url) {
        return imageHandler.downloadBitmap(url);
    }

    @Override
    public Bitmap loadImage(String imageName) {
        return imageHandler.loadImage(imageName);
    }

    @Override
    public String getImagePath(String imageName) {
        return imageHandler.getImagePath(imageName);
    }

    @Override
    public boolean isFileExists(String imageName) {
        return imageHandler.isFileExists(imageName);
    }

    @Override
    public void deleteImage(String imageName) {
        imageHandler.deleteImage(imageName);
    }
}
