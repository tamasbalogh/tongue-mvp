package baloght.tongue.ui.login;

import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import baloght.tongue.di.PerActivity;
import baloght.tongue.ui.base.MvpPresenter;

/**
 * Created by Balogh Tamas on 2018. 04. 01..
 */

@PerActivity
public interface LoginMvpPresenter <V extends LoginMvpView> extends MvpPresenter<V> {

    void checkLoggedInmode();
    void loginWithServer(EditText email, EditText password);
    void loginWithFacebook(LoginButton loginButton, CallbackManager callbackManager);
}
