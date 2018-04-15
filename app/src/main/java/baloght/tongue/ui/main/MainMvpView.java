package baloght.tongue.ui.main;

import com.facebook.CallbackManager;

import baloght.tongue.ui.base.MvpView;

/**
 * Created by Balogh Tamas on 2018. 04. 02..
 */

public interface MainMvpView extends MvpView {

    void showHomeFragment();
    void showStatisticsFragment();
    void showLogoutDialog();
    void openLoginActivity();
}
