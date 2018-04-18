package baloght.tongue.ui.fragment.home;

import android.util.Log;

import javax.inject.Inject;

import baloght.tongue.data.DataManager;
import baloght.tongue.ui.base.BasePresenter;
import baloght.tongue.utils.LogUtil;

/**
 * Created by baloght on 2018.04.12..
 */

public class HomePresenter <V extends HomeMvpView>  extends BasePresenter<V> implements HomeMvpPresenter<V> {

    @Inject
    public HomePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onProfilePicClicked() {

    }

    @Override
    public void onLetStartClicked() {
        getMvpView().OpenGameActivity();
    }

    @Override
    public void onViewInitialized() {
        getMvpView().updateUserName(getDataManager().getCurrentUserName());
    }
}