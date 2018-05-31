package baloght.tongue.ui.main.fragment.statistics;

import java.util.ArrayList;
import java.util.HashMap;

import baloght.tongue.di.PerActivity;
import baloght.tongue.ui.base.MvpPresenter;
import baloght.tongue.ui.fragment.statistics.StatisticsMvpView;

@PerActivity
public interface StatisticsMvpPresenter <V extends StatisticsMvpView> extends MvpPresenter<V> {

    HashMap<String, Integer> getDaysWhereUserPlayed();
}
