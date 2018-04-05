package baloght.tongue.ui.main;

import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.login.LoginManager;

import javax.inject.Inject;

import baloght.tongue.data.DataManager;
import baloght.tongue.ui.base.BasePresenter;

/**
 * Created by Balogh Tamas on 2018. 04. 02..
 */

public class MainPresenter <V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        Log.d("token: ",""+getDataManager().getAccessToken());
        Log.d("username: ",""+getDataManager().getCurrentUserName());
        Log.d("mode: ", "" + getDataManager().getCurrentUserLoggedInMode());
        Log.d("picurl: ","" + getDataManager().getCurrentUserProfilePicUrl());


        getMvpView().updateUserName(""+getDataManager().getCurrentUserName());
    }

    @Override
    public void logout() {
        if (getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_FB.getType()){
            LoginManager.getInstance().logOut();
            getDataManager().setUserAsLoggedOut();
            getMvpView().OpenLoginActivity();
        }

        if (getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER.getType()){
            getDataManager().setUserAsLoggedOut();
            getMvpView().OpenLoginActivity();
        }
    }
}
