package baloght.tongue.ui.game.activity;

import baloght.tongue.di.PerActivity;
import baloght.tongue.ui.base.MvpPresenter;

@PerActivity
public interface GameMvpPresenter<V extends GameMvpView> extends MvpPresenter<V> {

}
