package baloght.tongue.ui.fragment.statistics;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import baloght.tongue.BuildConfig;
import baloght.tongue.R;
import baloght.tongue.Tongue;
import baloght.tongue.di.ActivityContext;
import baloght.tongue.di.ApplicationContext;
import baloght.tongue.di.component.ActivityComponent;
import baloght.tongue.ui.base.BaseFragment;
import baloght.tongue.utils.DateUtils;
import baloght.tongue.utils.LogUtil;

/**
 * Created by baloght on 2018.04.12..
 */

public class StatisticsFragment extends BaseFragment implements StatisticsMvpView{

    @Inject
    StatisticsMvpPresenter<StatisticsMvpView> presenter;

    public static final String TAG = "StatisticsFragment";
    private PieChart chart;
    private ImageView dayPointer;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            presenter.onAttach(this);
        }

        for ( String date: DateUtils.getDatesOfWeek()) {
            LogUtil.log(date);
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
        showDayPointer(view);
    }

    private void setPieChart() {
        List<PieEntry> entries;
        entries = new ArrayList<>();
        entries.add(new PieEntry(1000,"Known Words"));
        entries.add(new PieEntry(3000, "Unknown Words"));

        PieDataSet dataSet = new PieDataSet(entries,"");
        PieData pieData = new PieData(dataSet);
        List<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.text));
        colors.add(getResources().getColor(R.color.buttons));
        dataSet.setColors(colors);
        chart.setData(pieData);
        chart.setHoleColor(getResources().getColor(R.color.background));
        chart.animateY(2000);
        chart.getDescription().setEnabled(false);
        //Known words, Total words
        chart.setDrawEntryLabels(false);
        chart.getLegend().setEnabled(false);
        chart.setCenterText("25%");
        chart.setCenterTextSizePixels(100);
    }

    private String calculateCenterPercentage(int known, int unknown){
        int sum = known + unknown;
        double percentage = known / sum;
        return (percentage * 100) + " %";
    }

    private void showDayPointer(View view){
        String name = "dayPointer" + DateUtils.getDayOfWeek();
        int id = getResources().getIdentifier(name,"id", BuildConfig.APPLICATION_ID);
        dayPointer = (ImageView) view.findViewById(id);
        dayPointer.setVisibility(View.VISIBLE);
    }
}
