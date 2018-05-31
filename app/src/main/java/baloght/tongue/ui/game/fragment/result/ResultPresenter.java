package baloght.tongue.ui.game.fragment.result;

import android.view.View;

import java.util.List;

import javax.inject.Inject;

import baloght.tongue.data.DataManager;
import baloght.tongue.data.db.Statistics;
import baloght.tongue.ui.base.BaseFragment;
import baloght.tongue.ui.base.BasePresenter;

public class ResultPresenter <V extends ResultMvpView> extends BasePresenter<V> implements ResultMvpPresenter<V> {

    @Inject
    public ResultPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onHomeMenuClicked() {
        getMvpView().goToHomeMenu();
    }

    @Override
    public void onNewGameClicked() {
        getMvpView().startNewGame();
    }

    @Override
    public long saveResultToDb(Statistics statistics) {
        return getDataManager().insertStatistics(statistics);
    }

    @Override
    public List<Statistics> getStatistics() {
        return getDataManager().getAllStatistics();
    }

    @Override
    public void deleteAllStatistics() {
        getDataManager().deleteAllStatistics();
    }
}
