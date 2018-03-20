package baloght.tongue.login;

import android.app.Activity;
import android.content.Context;
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


import baloght.tongue.R;
import baloght.tongue.register.RegisterView;
import baloght.tongue.utils.KeyboardUtils;

/**
 * Created by baloght on 2018.03.12..
 */

public class LoginView extends Activity implements LoginMVP.ViewOperations {

    private LoginMVP.ProvidedPresenterOperations presenter;
    private EditText email, password;
    private ProgressBar progressBar;
    private LoginButton logInWithFacebook;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
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

    public void logIn(View v) {
        presenter.validateCredentials(email,password);
    }

    public void logInWithFacebook(View v){
        logInWithFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(),"succes",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"cancel",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void signUp(View v) {
        startActivity(new Intent(this,RegisterView.class));
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void goToTheHomePage() {
        startActivity(new Intent(this,RegisterView.class));
        finish();
    }

    @Override
    public void showToast(Toast toast) {
        toast.show();
    }


    private void initViews(){
        logInWithFacebook =  findViewById(R.id.loginButtonLogInWithFacebook);
        callbackManager = CallbackManager.Factory.create();
        email = findViewById(R.id.loginEditTextEmail);
        password = findViewById(R.id.loginEditTextPassword);
        progressBar = findViewById(R.id.loginProgressBar);
        presenter = new LoginPresenter(this);
    }

}
