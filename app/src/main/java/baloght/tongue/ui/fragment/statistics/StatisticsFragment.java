package baloght.tongue.ui.fragment.statistics;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.Tongue;
import baloght.tongue.di.component.ActivityComponent;
import baloght.tongue.ui.base.BaseFragment;

/**
 * Created by baloght on 2018.04.12..
 */

public class StatisticsFragment extends BaseFragment implements StatisticsMvpView{

    @Inject
    StatisticsMvpPresenter<StatisticsMvpView> presenter;


    public static final String TAG = "StatisticsFragment";
    private PieChart chart;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            presenter.onAttach(this);
        }

        setUp(view);
        return view;
    }

    public static StatisticsFragment newInstance(){
        StatisticsFragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();
        //args.putInt("someInt", someInt);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setUp(View view) {
        chart = view.findViewById(R.id.fragmentStatisticsPieChart);
        setPieChart();
    }

    private void setPieChart() {
        List<PieEntry> entries;
        entries = new ArrayList<>();
        entries.add(new PieEntry(1500));
        entries.add(new PieEntry(3500));

        PieDataSet dataSet = new PieDataSet(entries,"");
        PieData pieData = new PieData(dataSet);
        List<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.text));
        colors.add(getResources().getColor(R.color.buttons));
        dataSet.setColors(colors);
        chart.setData(pieData);
        chart.setHoleColor(getResources().getColor(R.color.background));
        chart.animateY(2000);
    }
}
