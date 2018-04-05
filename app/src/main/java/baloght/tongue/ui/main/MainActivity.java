package baloght.tongue.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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
    public void updateUserName(String userName) {
        username.setText("Welcome " + userName);
    }

    @Override
    public void updateUserProfilePic(String userProfilePicUrl) {

    }

    @Override
    public void OpenLoginActivity() {
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void mainCircleImageView(View v){
        showMessage("Would you like to change pic?");
    }
    public void mainLetsStart(View v){
        showMessage("lets start");
    }

    public void mainLogOut(View v){
        presenter.logout();
        finish();
    }
}
