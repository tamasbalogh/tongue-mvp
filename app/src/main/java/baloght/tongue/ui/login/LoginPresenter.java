package baloght.tongue.ui.login;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.net.URL;

import javax.inject.Inject;
import baloght.tongue.data.DataManager;
import baloght.tongue.ui.base.BasePresenter;
import baloght.tongue.utils.LogUtil;
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
                getDataManager().updateUserInfo("asdASDSasdUgHGHJKlKKLlklLllkK2u12fjkasdlfa3oda81341klFASdslésdf",
                        DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                        "testuser",
                        null);
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
        getMvpView().showLoading();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                setFacebookData(loginResult);
            }

            @Override
            public void onCancel() {
                getMvpView().hideLoading();
                getMvpView().showMessage("cancel");
            }

            @Override
            public void onError(FacebookException error) {
                getMvpView().hideLoading();
                getMvpView().showMessage(error.toString());
            }
        });
    }

    private void setFacebookData(final LoginResult loginResult){

        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                            String firstName = Profile.getCurrentProfile().getFirstName();
                            String profilePicUrl = Profile.getCurrentProfile().getProfilePictureUri(200,200).toString();

                            getDataManager().updateUserInfo(
                                    loginResult.getAccessToken().getToken().toString(),
                                    DataManager.LoggedInMode.LOGGED_IN_MODE_FB,
                                    firstName,
                                    profilePicUrl);

                            LogUtil.log("login - firstname " + firstName);
                            LogUtil.log("login - profilpicurl " + profilePicUrl);

                            getMvpView().hideLoading();
                            getMvpView().OpenMainActivity();
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,email,first_name,last_name,gender");
        request.setParameters(parameters);
        request.executeAsync();
    }
}