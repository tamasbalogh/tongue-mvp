package baloght.tongue.ui.game.fragment.result;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import javax.inject.Inject;
import baloght.tongue.R;
import baloght.tongue.data.db.Statistics;
import baloght.tongue.di.component.ActivityComponent;
import baloght.tongue.ui.base.BaseFragment;
import baloght.tongue.ui.game.GameActivity;
import baloght.tongue.utils.DateUtils;
import baloght.tongue.utils.LogUtil;

public class ResultFragment extends BaseFragment implements ResultMvpView {

    public static final String TAG = "ResultFragment";
    private static final String BUNDLE_PERCENTAGE_TAG = "percentage";

    ImageView star1, star2, star3;
    Button homeButton, newgameButton;
    TextView score, scoreText;
    private int percentage;

    @Inject
    ResultMvpPresenter<ResultMvpView> presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result,container,false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            presenter.onAttach(this);
        }
        LogUtil.log("ResultFragment is loading...");
        return view;
    }

    @Override
    protected void setUp(View view) {
        LogUtil.log("ResultFragment setUp function is called...");
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            percentage = bundle.getInt(BUNDLE_PERCENTAGE_TAG);
        }

        homeButton = view.findViewById(R.id.fragmentResultButtonGoToHome);
        homeButton.setOnClickListener(this);
        newgameButton = view.findViewById(R.id.fragmentResultButtonNewGame);
        newgameButton.setOnClickListener(this);

        score = view.findViewById(R.id.fragmentResultTextViewScore);
        scoreText = view.findViewById(R.id.fragmentResultTextViewScoreText);

        score.setText(percentage + " %");
        scoreText.setText(setScoreText());

        star1 = view.findViewById(R.id.fragmentResultImageViewStar1);
        star2 = view.findViewById(R.id.fragmentResultImageViewStar2);
        star3 = view.findViewById(R.id.fragmentResultImageViewStar3);

        LogUtil.log("saved statistics id: " + presenter.saveResultToDb(new Statistics(
                DateUtils.getToday(),
                starsAnimation(),
                percentage)));
    }

    public static ResultFragment newInstance(int percentage){
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_PERCENTAGE_TAG, percentage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void startNewGame() {
        getActivity().finish();
        startActivity(new Intent(getActivity(),GameActivity.class));
    }

    @Override
    public void goToHomeMenu() {
        getActivity().finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fragmentResultButtonNewGame){
            presenter.onNewGameClicked();
        }

        if(v.getId() == R.id.fragmentResultButtonGoToHome){
            presenter.onHomeMenuClicked();
        }
    }

    private String setScoreText(){
        String result = "";
        if(percentage == 100 ){
            result ="Excellent!";
        } else if (percentage <= 99 && percentage >= 80){
            result ="Well done!";
        } else  if (percentage <= 79 && percentage >= 60){
            result ="Good!";
        } else {
            result ="Try again!";
        }
        return result;
    }

    private int  starsAnimation(){

        int count = 0;

        if(percentage == 100 ){
            count = 3;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    star2.setVisibility(View.VISIBLE);
                }
            }, 1000);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    star1.setVisibility(View.VISIBLE);
                }
            }, 2000);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    star3.setVisibility(View.VISIBLE);
                }
            }, 3000);

        } else if (percentage <= 99 && percentage >= 80){
            count = 2;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    star2.setVisibility(View.VISIBLE);
                }
            }, 1000);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    star1.setVisibility(View.VISIBLE);
                }
            }, 2000);

        } else  if (percentage <= 79 && percentage >= 60){
            count = 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    star2.setVisibility(View.VISIBLE);
                }
            }, 1000);
        }
        return  count;
    }
}
