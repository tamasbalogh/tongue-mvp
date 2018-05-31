package baloght.tongue.ui.main;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import javax.inject.Inject;
import baloght.tongue.R;
import baloght.tongue.ui.base.BaseActivity;
import baloght.tongue.ui.fragment.logout.LogoutDialogFragment;
import baloght.tongue.ui.login.LoginActivity;
import baloght.tongue.ui.main.fragment.home.HomeFragment;
import baloght.tongue.ui.main.fragment.statistics.StatisticsFragment;

/**
 * Created by Balogh Tamas on 2018. 04. 02..
 */

public class MainActivity extends BaseActivity implements MainMvpView{

    @Inject
    MainMvpPresenter<MainMvpView> presenter;

    private BottomNavigationView bottomNavigationView;
    private FragmentManager manager = getFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        presenter.onAttach(MainActivity.this);

        if (savedInstanceState == null){
            presenter.onHomeMenuClicked();
        }

        setUp();
    }

    @Override
    protected void setUp() {
        bottomNavigationView = findViewById(R.id.mainBottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                if(bottomNavigationView.getSelectedItemId()!=R.id.action_home){
                                    presenter.onHomeMenuClicked();
                                }
                                break;
                            case R.id.action_statistics:
                                if(bottomNavigationView.getSelectedItemId()!=R.id.action_statistics) {
                                    presenter.onStatisticsMenuClicked();
                                }
                                break;
                            case R.id.action_logout:
                                presenter.onLogoutMenuClicked();
                                break;
                        }
                        return true;
                    }
                });
    }

    @Override
    public void showHomeFragment() {
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_main, HomeFragment.newInstance(), HomeFragment.TAG);
        transaction.commit();
    }

    @Override
    public void showStatisticsFragment() {
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_main, StatisticsFragment.newInstance(), StatisticsFragment.TAG);
        transaction.commit();
    }

    @Override
    public void showLogoutDialog() {
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_main, LogoutDialogFragment.newInstance(), LogoutDialogFragment.TAG);
        transaction.commit();
    }

    @Override
    public void openLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
