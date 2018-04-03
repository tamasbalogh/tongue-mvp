package baloght.tongue.data.network;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Balogh Tamas on 2018. 04. 02..
 */

public interface ApiHelper {

    void doRegister(RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler);
    void doLoginWithServer(RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler);
    void doLoginWithFacebook(LoginButton loginButton, CallbackManager callbackManager, FacebookCallback<LoginResult> callback);
    void getUserData(String token, AsyncHttpResponseHandler asyncHttpResponseHandler);

}
