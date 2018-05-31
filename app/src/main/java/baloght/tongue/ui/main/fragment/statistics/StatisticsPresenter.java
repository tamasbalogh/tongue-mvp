package baloght.tongue.ui.main.fragment.statistics;

import java.util.HashMap;

import javax.inject.Inject;

import baloght.tongue.data.DataManager;
import baloght.tongue.ui.base.BasePresenter;
import baloght.tongue.ui.fragment.statistics.StatisticsMvpView;
import baloght.tongue.utils.DateUtils;
import baloght.tongue.utils.LogUtil;

public class StatisticsPresenter <V extends StatisticsMvpView>  extends BasePresenter<V> implements StatisticsMvpPresenter<V> {

    @Inject
    public StatisticsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public HashMap<String, Integer> getDaysWhereUserPlayed() {
        HashMap<String, Integer> result = new HashMap<>();
        String [] datesOfWeek = DateUtils.getDatesOfWeek();
        for(String date : datesOfWeek){
            result.put(date,getDataManager().getNumberOfGamesByDate(date));
            LogUtil.log(date + " - " + getDataManager().getNumberOfGamesByDate(date));
        }
        return result;
    }
}
