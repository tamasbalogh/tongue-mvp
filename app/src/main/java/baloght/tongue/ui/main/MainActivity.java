package baloght.tongue.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.ui.base.BaseActivity;
import baloght.tongue.ui.game.GameActivity;
import baloght.tongue.ui.login.LoginActivity;

/**
 * Created by Balogh Tamas on 2018. 04. 02..
 */

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> presenter;
    private TextView username;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        presenter.onAttach(MainActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {
        username = findViewById(R.id.mainTextViewUserName);
        bottomNavigationView = findViewById(R.id.mainBottomNavigation);
        presenter.onViewInitialized();
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_main:

                                break;
                            case R.id.action_statistics:

                                break;
                            case R.id.action_logout:
                                presenter.onLogoutClicked();
                                break;
                        }
                        return true;
                    }
                });
    }

    @Override
    public void updateUserName(String userName){
        this.username.setText("Welcome " + userName);
    }

    @Override
    public void updateUserProfilePic(String userProfilePicUrl) { }

    @Override
    public void OpenLoginActivity() {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    @Override
    public void OpenGameActivity() {
        startActivity(new Intent(this,GameActivity.class));
    }

    public void mainCircleImageView(View v){
        showMessage("Would you like to change pic?");
    }

    public void mainLetsStart(View v){
        presenter.onLetStartClicked();
    }
}
