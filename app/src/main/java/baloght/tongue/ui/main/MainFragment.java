package baloght.tongue.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import baloght.tongue.ui.base.BaseFragment;
import baloght.tongue.ui.base.MvpView;

/**
 * Created by baloght on 2018.04.11..
 */

public class MainFragment extends BaseFragment implements MainMvpView{

    public static final String TAG = "MainFragment";

    @Inject
    MainMvpPresenter<MainMvpView> presenter;


    @Override
    public void updateUserName(String userName) {

    }

    @Override
    public void updateUserProfilePic(String userProfilePicUrl) {

    }

    @Override
    public void OpenLoginActivity() {

    }

    @Override
    public void OpenGameActivity() {

    }

    @Override
    protected void setUp(View view) {

    }
}
