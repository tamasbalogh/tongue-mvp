package baloght.tongue.ui.main.fragment.statistics;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import baloght.tongue.BuildConfig;
import baloght.tongue.R;
import baloght.tongue.di.component.ActivityComponent;
import baloght.tongue.ui.base.BaseFragment;
import baloght.tongue.ui.fragment.statistics.StatisticsMvpView;
import baloght.tongue.utils.DateUtils;
import baloght.tongue.utils.LogUtil;

/**
 * Created by baloght on 2018.04.12..
 */

public class StatisticsFragment extends BaseFragment implements StatisticsMvpView {

    @Inject
    StatisticsMvpPresenter<StatisticsMvpView> presenter;

    public static final String TAG = "StatisticsFragment";
    private PieChart chart;
    private ImageView dayPointer, dayIndicator;
    private TextView dailyPerformance;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            presenter.onAttach(this);
        }

        LogUtil.log("StatisticsFragment is loading...");
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
        LogUtil.log("StatisticsFragment setUp function is called...");
        chart = view.findViewById(R.id.fragmentStatisticsPieChart);
        setPieChart();
        showDayPointer(view);
        showDayIndicator(view,presenter.getDaysWhereUserPlayed());
    }

    private void setPieChart() {
        List<PieEntry> entries;
        entries = new ArrayList<>();
        entries.add(new PieEntry(1000,"Known Words"));
        entries.add(new PieEntry(3000, "Unknown Words"));
        PieDataSet dataSet = new PieDataSet(entries,"");
        dataSet.setDrawValues(false);
        PieData pieData = new PieData(dataSet);
        List<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.text));
        colors.add(getResources().getColor(R.color.buttons));
        dataSet.setColors(colors);
        chart.setData(pieData);
        chart.setHoleColor(getResources().getColor(R.color.background));
        chart.setTouchEnabled(false);
        chart.animateY(2000);
        chart.getDescription().setEnabled(false);
        //Known words, Total words
        chart.setDrawEntryLabels(false);
        chart.getLegend().setEnabled(false);
        chart.setCenterText(Float.toString(calculateCenterPercentage(1000,3000)));
        chart.setCenterTextSizePixels(100);
    }

    private float calculateCenterPercentage(int known, int unknown){
        float max = known + unknown;
        return (known * 100.0f) / max;
    }

    private void showDayPointer(View view){
        String name = "dayPointer" + DateUtils.getDayOfWeek();
        int id = getResources().getIdentifier(name,"id", BuildConfig.APPLICATION_ID);
        dayPointer = (ImageView) view.findViewById(id);
        dayPointer.setVisibility(View.VISIBLE);
    }

    private void showDayIndicator(View view, HashMap<String, Integer> numberOfGamesPerDay){
        String[] dates = DateUtils.getDatesOfWeek();
        for (int i = 0; i < dates.length; i++) {
               if(numberOfGamesPerDay.get(dates[i]) > 0){
                   LogUtil.log("dayIndicator" + i);
                   int dayIndicatorId = getResources().getIdentifier("dayIndicator" + i
                           ,"id"
                           ,BuildConfig.APPLICATION_ID);
                   dayIndicator = view.findViewById(dayIndicatorId);
                   dayIndicator.setImageResource(R.drawable.day_indicator_true);

                   LogUtil.log("dailyPerformance" + i);
                   int dailyPerformanceId = getResources().getIdentifier("dailyPerformance" + i,"id", BuildConfig.APPLICATION_ID);
                   dailyPerformance = view.findViewById(dailyPerformanceId);
                   dailyPerformance.setText(numberOfGamesPerDay.get(dates[i]).toString());
               }
        }
    }
}
