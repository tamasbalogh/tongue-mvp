package baloght.tongue.ui.main;

import baloght.tongue.di.PerActivity;
import baloght.tongue.ui.base.MvpPresenter;

/**
 * Created by Balogh Tamas on 2018. 04. 02..
 */

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
}
