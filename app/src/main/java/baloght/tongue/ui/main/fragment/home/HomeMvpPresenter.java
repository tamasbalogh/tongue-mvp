package baloght.tongue.ui.fragment.home;

import baloght.tongue.di.PerActivity;
import baloght.tongue.ui.base.MvpPresenter;
import baloght.tongue.ui.login.LoginMvpView;

/**
 * Created by baloght on 2018.04.12..
 */

@PerActivity
public interface HomeMvpPresenter <V extends HomeMvpView> extends MvpPresenter<V> {

    void saveProfilePicPath(String path);
    void onProfilePicClicked();
    void onLetStartClicked();
    void onViewInitialized();
}
