package baloght.tongue.ui.fragment.statistics;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import baloght.tongue.R;
import baloght.tongue.ui.fragment.home.HomeFragment;

/**
 * Created by baloght on 2018.04.12..
 */

public class StatisticsFragment extends Fragment {

    public static final String TAG = "StatisticsFragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    public static StatisticsFragment newInstance(){
        StatisticsFragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();
        //args.putInt("someInt", someInt);
        fragment.setArguments(args);
        return fragment;
    }
}
