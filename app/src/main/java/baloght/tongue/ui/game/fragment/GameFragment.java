package baloght.tongue.ui.game.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.di.component.ActivityComponent;
import baloght.tongue.ui.base.BaseFragment;
import baloght.tongue.ui.fragment.home.HomeFragment;
import baloght.tongue.utils.GameUtils.BaseGame;
import baloght.tongue.utils.GameUtils.DragListener;
import baloght.tongue.utils.GameUtils.FirstTypeOfBaseGame;
import baloght.tongue.utils.GameUtils.TouchListener;

/**
 * Created by baloght on 2018.04.27..
 */

public class GameFragment extends BaseFragment implements GameFMvpView{

    public static final String TAG = "GameFragment";

    @Inject
    GameFMvpPresenter<GameFMvpView> presenter;

    ImageView imageView0, imageView1;
    TextView word;

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

    public static GameFragment newInstance(ArrayList<FirstTypeOfBaseGame> games){
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("games", (ArrayList<? extends Parcelable>) games);
        //args.putInt("someInt", someInt);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setUp(View view) {
        imageView0 = view.findViewById(R.id.gameImageView0);
        imageView1 = view.findViewById(R.id.gameImageView1);
        word = view.findViewById(R.id.gameTextViewTarget);

        imageView0.setOnTouchListener(new TouchListener());
        imageView1.setOnTouchListener(new TouchListener());

        word.setOnDragListener(new DragListener(view.getContext(), imageView0, imageView1, word));
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }
}
