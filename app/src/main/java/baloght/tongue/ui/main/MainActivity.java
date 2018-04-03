package baloght.tongue.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.ui.base.BaseActivity;
import baloght.tongue.ui.login.LoginActivity;

/**
 * Created by Balogh Tamas on 2018. 04. 02..
 */

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> presenter;

    private TextView username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        presenter.onAttach(MainActivity.this);
        initViews();
    }

    private void initViews() {
        username =  findViewById(R.id.mainTextViewUserName);
    }

    @Override
    public void openLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void updateUserName(String userName) {
        username.setText("Welcome " + userName);
    }

    @Override
    public void updateUserEmail(String userEmail) {

    }

    @Override
    public void updateUserProfilePic(String userProfilePicUrl) {

    }
}
