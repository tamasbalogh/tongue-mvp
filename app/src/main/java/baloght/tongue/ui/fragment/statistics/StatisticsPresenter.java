package baloght.tongue.ui.fragment.statistics;

import javax.inject.Inject;

import baloght.tongue.data.DataManager;
import baloght.tongue.ui.base.BasePresenter;

public class StatisticsPresenter <V extends StatisticsMvpView>  extends BasePresenter<V> implements StatisticsMvpPresenter<V> {

    @Inject
    public StatisticsPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
