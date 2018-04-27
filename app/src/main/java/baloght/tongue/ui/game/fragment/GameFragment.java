package baloght.tongue.ui.game.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.di.component.ActivityComponent;
import baloght.tongue.ui.base.BaseFragment;
import baloght.tongue.ui.fragment.home.HomeFragment;

/**
 * Created by baloght on 2018.04.27..
 */

public class GameFragment extends BaseFragment implements GameFMvpView{

    public static final String TAG = "GameFragment";

    @Inject
    GameFMvpPresenter<GameFMvpView> presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game,container,false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            presenter.onAttach(this);
        }

        setUp(view);

        return view;
    }

    public static GameFragment newInstance(){
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        //args.putInt("someInt", someInt);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }
}
