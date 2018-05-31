package baloght.tongue.ui.fragment.home;

import android.util.Log;


import javax.inject.Inject;

import baloght.tongue.data.DataManager;
import baloght.tongue.ui.base.BasePresenter;

/**
 * Created by baloght on 2018.04.12..
 */

public class HomePresenter <V extends HomeMvpView>  extends BasePresenter<V> implements HomeMvpPresenter<V> {

    @Inject
    public HomePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void saveProfilePicPath(String path) {
        getDataManager().setStoredProfilePicPath(path);
    }

    @Override
    public void onProfilePicClicked() {
        getMvpView().showMessage("Would you like to change profile picture?");
    }

    @Override
    public void onLetStartClicked() {
        getMvpView().OpenGameActivity();
    }

    @Override
    public void onViewInitialized() {

        //logged in with fb
        if(getDataManager().getUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_FB.getType()){
            getMvpView().updateUserName(getDataManager().getUserName());
            getMvpView().updateUserProfilePic(DataManager.LoggedInMode.LOGGED_IN_MODE_FB,
                    getDataManager().getUserProfilePicUrl(),
                    getDataManager().getStoredProfilePicPath());
        }

        //logged in with server
        if(getDataManager().getUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER.getType()){
            getMvpView().updateUserName(getDataManager().getUserName());
            getMvpView().updateUserProfilePic(DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                    getDataManager().getUserProfilePicUrl(),
                    getDataManager().getStoredProfilePicPath());
        }

    }
}
