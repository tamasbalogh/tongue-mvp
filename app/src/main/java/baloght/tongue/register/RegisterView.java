package baloght.tongue.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import baloght.tongue.R;
import baloght.tongue.login.LoginView;
import baloght.tongue.utils.KeyboardUtils;

/**
 * Created by Balogh Tamas on 2018. 03. 12..
 */

public class RegisterView extends Activity implements RegisterMVP.ViewOperations {

    Button signUp;
    EditText email, user, password;
    ProgressBar progressBar;

    RegisterMVP.ProvidedPresenterOperations presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        presenter = new RegisterPresenter(this);

        initViews();
    }

    private void initViews() {
        signUp = findViewById(R.id.registerButtonSignUp);
        email = findViewById(R.id.registerEditTextEmail);
        user = findViewById(R.id.registerEditTextUser);
        password = findViewById(R.id.registerEditTextPassword);
        progressBar = findViewById(R.id.registerProgressBar);
    }

    void registerLayout(View v){
        KeyboardUtils.HideKeyboard(this);
    }

    void registerLogo(View v){
        KeyboardUtils.HideKeyboard(this);
    }

    void registerButton(View v){
        presenter.register(email,user,password);
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
    public void showToast(Toast toast) {
        toast.show();
    }

    @Override
    public void goToTheLoginPage() {
        startActivity(new Intent(this, LoginView.class));
        finish();
    }
}
