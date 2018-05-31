package baloght.tongue.ui.game;

import java.util.List;

import javax.inject.Inject;

import baloght.tongue.data.DataManager;
import baloght.tongue.data.db.Statistics;
import baloght.tongue.ui.base.BasePresenter;

public class GamePresenter <V extends GameMvpView> extends BasePresenter<V> implements GameMvpPresenter<V> {

    @Inject
    public GamePresenter(DataManager dataManager) {
        super(dataManager);
    }
}
