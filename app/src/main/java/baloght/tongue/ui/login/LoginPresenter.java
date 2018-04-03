package baloght.tongue.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import baloght.tongue.data.DataManager;
import baloght.tongue.ui.base.BasePresenter;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Balogh Tamas on 2018. 03. 12..
 */

public class LoginPresenter <V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    @Inject
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void checkLoggedInmode() {
        if(getDataManager().getCurrentUserLoggedInMode() != DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()){
            getMvpView().OpenMainActivity();
        }
    }

    @Override
    public void loginWithServer(final EditText email, EditText password) {

        if(email.getText().toString().isEmpty()){
            getMvpView().warnUser(email);
            return;
        }

        if(password.getText().toString().isEmpty()){
            getMvpView().warnUser(password);
            return;
        }

        final RequestParams params = new RequestParams();
        params.put("email", email.getText());
        params.put("password", password.getText());

        getDataManager().doLoginWithServer(params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                getMvpView().showLoading();
                getMvpView().hideKeyboard();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                getMvpView().hideLoading();
                getMvpView().OpenMainActivity();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                getDataManager().updateUserInfo("takenfromresponsebody",
                        DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                        email.getText().toString(),"profilePicURL");
                getMvpView().hideLoading();
                getMvpView().OpenMainActivity();
            }

            @Override
            public void onRetry(int retryNo) {
                getMvpView().showMessage("Request retry no. " +  retryNo + "/3");
            }
        });
    }

    @Override
    public void loginWithFacebook(final LoginButton loginButton, CallbackManager callbackManager) {

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            getDataManager().setCurrentUserEmail(object.getString("email"));
                        }catch (Exception e){
                            getMvpView().showMessage(e.toString());
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "email");
                request.setParameters(parameters);
                request.executeAsync();

                getDataManager().updateUserInfo(loginResult.getAccessToken().getToken(),
                        DataManager.LoggedInMode.LOGGED_IN_MODE_FB, getDataManager().getCurrentUserEmail(),"profilePicURL");
                getMvpView().hideLoading();
                getMvpView().OpenMainActivity();
            }

            @Override
            public void onCancel() {
                getMvpView().showMessage("Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                getMvpView().showMessage(error.toString());
            }
        });
    }
}
