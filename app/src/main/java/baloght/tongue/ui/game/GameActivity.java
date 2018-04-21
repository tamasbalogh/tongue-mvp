package baloght.tongue.ui.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.utils.DragListener;
import baloght.tongue.ui.base.BaseActivity;
import baloght.tongue.utils.TouchListener;


public class GameActivity extends BaseActivity implements GameMvpView {

    ImageView imageView0, imageView1;
    TextView textView;
    ConstraintLayout root;

    @Inject
    GameMvpPresenter<GameMvpView> presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getActivityComponent().inject(this);
        presenter.onAttach(GameActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        root = findViewById(R.id.gameLayout);
        imageView0 = findViewById(R.id.gameImageView0);
        imageView1 = findViewById(R.id.gameImageView1);
        textView = findViewById(R.id.gameTextViewTarget);

        imageView0.setOnTouchListener(new TouchListener());
        imageView1.setOnTouchListener(new TouchListener());

        DragListener dragListener = new DragListener(this, imageView0, imageView1, textView);
        textView.setOnDragListener(dragListener);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }
}
