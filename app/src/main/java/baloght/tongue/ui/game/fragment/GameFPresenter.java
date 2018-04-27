package baloght.tongue.ui.game.fragment;

import javax.inject.Inject;

import baloght.tongue.data.DataManager;
import baloght.tongue.ui.base.BasePresenter;

/**
 * Created by baloght on 2018.04.27..
 */

public class GameFPresenter<V extends GameFMvpView> extends BasePresenter<V> implements GameFMvpPresenter<V> {

    @Inject
    public GameFPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
