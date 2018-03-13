package baloght.tongue.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import baloght.tongue.R;

/**
 * Created by baloght on 2018.03.12..
 */

public class LoginView extends Activity implements LoginMVP.ViewOperations{

    LoginMVP.ProvidedPresenterOperations presenter;

    TextView signUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);
        signUp = findViewById(R.id.loginTextViewSignUp);
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public void showToast(Toast toast) {
        toast.show();
    }

    @Override
    public void clearEditText() {

    }

    public void signUp(View v){
        presenter.goToRegisterPage(this);
    }
}
