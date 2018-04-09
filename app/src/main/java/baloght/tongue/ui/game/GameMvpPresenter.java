package baloght.tongue.ui.game;

import baloght.tongue.di.PerActivity;
import baloght.tongue.ui.base.MvpPresenter;
import baloght.tongue.ui.login.LoginMvpView;

@PerActivity
public interface GameMvpPresenter<V extends GameMvpView> extends MvpPresenter<V> {

}
