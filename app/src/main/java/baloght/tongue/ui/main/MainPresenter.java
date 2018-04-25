package baloght.tongue.ui.main;

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
    public void onHomeMenuClicked() {
        getMvpView().showHomeFragment();
    }

    @Override
    public void onStatisticsMenuClicked() {
        getMvpView().showStatisticsFragment();
    }

    @Override
    public void onLogoutMenuClicked() {
        if (getDataManager().getUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_FB.getType()){
            LoginManager.getInstance().logOut();
            getDataManager().setUserAsLoggedOut();
            getMvpView().openLoginActivity();
        }

        if (getDataManager().getUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER.getType()){
            getDataManager().setUserAsLoggedOut();
            getMvpView().openLoginActivity();
        }
        //getMvpView().showLogoutDialog();
    }
}
