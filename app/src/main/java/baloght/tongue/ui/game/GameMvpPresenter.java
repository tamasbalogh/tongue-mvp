package baloght.tongue.ui.game;

import java.util.List;

import baloght.tongue.data.db.Statistics;
import baloght.tongue.di.PerActivity;
import baloght.tongue.ui.base.MvpPresenter;

@PerActivity
public interface GameMvpPresenter<V extends GameMvpView> extends MvpPresenter<V> {

}
