package baloght.tongue.ui.game.fragment.result;

import java.util.List;

import baloght.tongue.data.db.Statistics;
import baloght.tongue.di.PerActivity;
import baloght.tongue.ui.base.MvpPresenter;

@PerActivity
public interface ResultMvpPresenter <V extends ResultMvpView> extends MvpPresenter<V> {
    void onHomeMenuClicked();
    void onNewGameClicked();
    long saveResultToDb(Statistics statistics);
    List<Statistics> getStatistics();
    void deleteAllStatistics();
}
