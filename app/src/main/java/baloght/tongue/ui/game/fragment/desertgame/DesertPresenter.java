package baloght.tongue.ui.game.fragment.desertgame;

import javax.inject.Inject;

import baloght.tongue.data.DataManager;
import baloght.tongue.ui.base.BasePresenter;

/**
 * Created by baloght on 2018.04.27..
 */

public class DesertPresenter<V extends DesertMvpView> extends BasePresenter<V> implements DesertMvpPresenter<V> {

    @Inject
    public DesertPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
