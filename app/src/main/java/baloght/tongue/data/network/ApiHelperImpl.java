package baloght.tongue.data.network;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import javax.inject.Inject;
import javax.inject.Singleton;
import baloght.tongue.di.AsyncClient;

/**
 * Created by Balogh Tamas on 2018. 04. 02..
 */

@Singleton
public class ApiHelperImpl implements ApiHelper {

    private static final String API_KEY_USER_TOKEN = "API_KEY_TOKEN";

    private AsyncHttpClient client;

    @Inject
    public ApiHelperImpl(@AsyncClient AsyncHttpClient client) {
        this.client = client;
        this.client.setMaxRetriesAndTimeout(3, 500);
    }


    private void setAccesToken(String token) {
        client.addHeader(API_KEY_USER_TOKEN,token);
    }


    private void removeAccessToken() {
        client.removeHeader(API_KEY_USER_TOKEN);
    }

    @Override
    public void doRegister(RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        client.post(ApiEndpoint.REGISTER, requestParams, asyncHttpResponseHandler);
    }

    @Override
    public void doLoginWithServer(RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        client.post(ApiEndpoint.LOGIN,requestParams,asyncHttpResponseHandler);
    }

    @Override
    public void doLoginWithFacebook(LoginButton loginButton, CallbackManager callbackManager, FacebookCallback<LoginResult> callback) {
        loginButton.registerCallback(callbackManager,callback);
    }

    @Override
    public void getUserData(String token, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        setAccesToken(token);
        client.get(ApiEndpoint.MAINPAGE,asyncHttpResponseHandler);
    }
}