package baloght.tongue.ui.game.fragment.desertgame;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.di.component.ActivityComponent;
import baloght.tongue.ui.base.BaseFragment;
import baloght.tongue.ui.game.GameActivity;
import baloght.tongue.utils.Constants;
import baloght.tongue.utils.GameUtils.BaseGame;
import baloght.tongue.utils.GameUtils.DragListener;
import baloght.tongue.utils.GameUtils.TouchListener;
import baloght.tongue.utils.LogUtil;

/**
 * Created by baloght on 2018.04.27..
 */

public class DesertFragment extends BaseFragment implements DesertMvpView, DragListener.Response {

    public static final String TAG = "DesertFragment";
    private static final String BUNDLE_GAME_TAG = "desert_game";

    @Inject
    DesertMvpPresenter<DesertMvpView> presenter;

    ImageView imageView0, imageView1;
    TextView word;

    BaseGame game;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game,container,false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            presenter.onAttach(this);
        }

        return view;
    }

    public static DesertFragment newInstance(BaseGame game){
        DesertFragment fragment = new DesertFragment();
        Bundle args = new Bundle();
        args.putParcelable(BUNDLE_GAME_TAG, game);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setUp(View view) {
        Bundle bundle = this.getArguments();
        if (bundle != null){
            game = bundle.getParcelable(BUNDLE_GAME_TAG);
            imageView0 = view.findViewById(R.id.gameImageView0);
            imageView1 = view.findViewById(R.id.gameImageView1);
            word = view.findViewById(R.id.gameTextViewTarget);

            imageView0.setImageURI(Uri.parse((game.getImage0().getUri())));
            imageView1.setImageURI(Uri.parse((game.getImage1().getUri())));
            word.setText(game.getWord());

            imageView0.setOnTouchListener(new TouchListener());
            imageView1.setOnTouchListener(new TouchListener());

            word.setOnDragListener(new DragListener(view.getContext(),imageView0,imageView1,word, this));
        } else {
            LogUtil.log("Problem with bundle in gamefragment");
        }
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void selectionFinish(int selected) {
        LogUtil.log("selected image view: " + selected);
        LogUtil.log("answer: " + game.getAnswer());
        ((GameActivity) getActivity()).nextGame(selected);
    }
}
