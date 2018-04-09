package baloght.tongue.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import java.security.Permission;

import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.ui.base.BaseActivity;
import baloght.tongue.ui.main.MainActivity;
import baloght.tongue.ui.register.RegisterActivity;
import baloght.tongue.ui.splash.SplashActivity;
import baloght.tongue.utils.KeyboardUtils;

/**
 * Created by baloght on 2018.03.12..
 */

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginMvpPresenter<LoginMvpView> presenter;

    private EditText email, password;
    private ProgressBar progressBar;
    private LoginButton logInWithFacebook;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getActivityComponent().inject(this);
        presenter.onAttach(LoginActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        logInWithFacebook =  findViewById(R.id.loginButtonLogInWithFacebook);
        callbackManager = CallbackManager.Factory.create();
        email = findViewById(R.id.loginEditTextEmail);
        password = findViewById(R.id.loginEditTextPassword);
        progressBar = findViewById(R.id.loginProgressBar);
        setProgressBar(progressBar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    public void layoutOnClick(View v) {
        KeyboardUtils.HideKeyboard(this);
    }

    public void logoOnClick(View v) {
        KeyboardUtils.HideKeyboard(this);
    }

    public void logInWithServer(View v) {
        presenter.loginWithServer(email,password);
    }

    public void logInWithFacebook(View v){ presenter.loginWithFacebook(logInWithFacebook, callbackManager);}

    public void signUp(View v) {
        startActivity(new Intent(this,RegisterActivity.class));
    }

    @Override
    public void OpenMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }
}
