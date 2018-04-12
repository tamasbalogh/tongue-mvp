package baloght.tongue.ui.main;

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
    public void onHomeMenuClicked() {
        getMvpView().showHomeFragment();
    }

    @Override
    public void onStatisticsMenuClicked() {
        getMvpView().showStatisticsFragment();
    }

    @Override
    public void onLogoutMenuClicked() {
        getMvpView().showLogoutDialog();
    }


    /*
    @Override
    public void onLogoutClicked() {
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

    @Override
    public void onLetStartClicked() {
        getMvpView().OpenGameActivity();
    }

    @Override
    public void onViewInitialized() {
        Log.d("login", "main initialized!!");
           getMvpView().updateUserName(getDataManager().getCurrentUserName());
    }*/
}
