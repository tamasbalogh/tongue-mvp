package baloght.tongue.ui.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.game.DragListener;
import baloght.tongue.game.LongClickListener;
import baloght.tongue.ui.base.BaseActivity;
import baloght.tongue.ui.base.MvpView;


public class GameActivity extends BaseActivity implements GameMvpView {

    ImageView imageView0, imageView1;
    TextView textView;

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
        imageView0 = findViewById(R.id.gameImageView0);
        imageView1 = findViewById(R.id.gameImageView1);
        textView = findViewById(R.id.gameTextViewTarget);

        imageView0.setOnLongClickListener(new LongClickListener());
        imageView1.setOnLongClickListener(new LongClickListener());

        DragListener dragListener = new DragListener(this, imageView0, imageView1, textView);
        textView.setOnDragListener(dragListener);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }
}
