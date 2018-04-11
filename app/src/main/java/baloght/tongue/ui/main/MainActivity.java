package baloght.tongue.ui.main;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.ui.base.BaseActivity;
import baloght.tongue.ui.fragments.MainFragment;

/**
 * Created by Balogh Tamas on 2018. 04. 02..
 */

public class MainActivity extends BaseActivity implements MainMvpView{

    @Inject
    MainMvpPresenter<MainMvpView> presenter;
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
        bottomNavigationView = findViewById(R.id.mainBottomNavigation);

        FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, new MainFragment());
        transaction.commit();

        //presenter.onViewInitialized();
        /*bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_main:

                                break;
                            case R.id.action_statistics:

                                break;
                            case R.id.action_logout:
                                //presenter.onLogoutClicked();
                                break;
                        }
                        return true;
                    }
                });*/
    }

    /*
    @Override
    public void updateUserName(String userName){

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
    */
}
