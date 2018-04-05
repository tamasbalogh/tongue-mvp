package baloght.tongue.ui.splash;

import javax.inject.Inject;

import baloght.tongue.data.DataManager;
import baloght.tongue.ui.base.BasePresenter;

/**
 * Created by baloght on 2018.04.04..
 */

public class SplashPresenter <V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        decideNextActivity();
    }

    private void decideNextActivity() {
        if (getDataManager().getCurrentUserLoggedInMode()
                == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getMvpView().openLoginActivity();
        } else {
            getMvpView().openMainActivity();
        }
    }
}
