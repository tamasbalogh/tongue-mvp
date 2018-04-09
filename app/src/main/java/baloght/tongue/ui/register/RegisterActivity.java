package baloght.tongue.ui.register;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import javax.inject.Inject;
import baloght.tongue.R;
import baloght.tongue.ui.base.BaseActivity;
import baloght.tongue.ui.login.LoginActivity;
import baloght.tongue.utils.KeyboardUtils;

/**
 * Created by Balogh Tamas on 2018. 03. 12..
 */

public class RegisterActivity extends BaseActivity implements RegisterMvpView {

    @Inject
    RegisterMvpPresenter<RegisterMvpView> presenter;

    Button signUp;
    EditText email, user, password;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getActivityComponent().inject(this);
        presenter.onAttach(RegisterActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        signUp = findViewById(R.id.registerButtonSignUp);
        email = findViewById(R.id.registerEditTextEmail);
        user = findViewById(R.id.registerEditTextUser);
        password = findViewById(R.id.registerEditTextPassword);
        progressBar = findViewById(R.id.registerProgressBar);
        setProgressBar(progressBar);
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
    public void openLoginPage() {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }
}
