package baloght.tongue.ui.game.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.ui.fragment.home.HomeFragment;
import baloght.tongue.ui.game.fragment.GameFragment;
import baloght.tongue.utils.GameUtils.BaseGame;
import baloght.tongue.utils.GameUtils.DragListener;
import baloght.tongue.ui.base.BaseActivity;
import baloght.tongue.utils.GameUtils.FirstTypeOfBaseGame;
import baloght.tongue.utils.GameUtils.TouchListener;
import baloght.tongue.utils.ImageHandler;
import baloght.tongue.utils.LoadJSON;
import baloght.tongue.utils.LogUtil;


public class GameActivity extends BaseActivity implements GameMvpView {

    @Inject
    GameMvpPresenter<GameMvpView> presenter;

    private static int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getActivityComponent().inject(this);
        presenter.onAttach(GameActivity.this);
        setUp();
    }



    private List<FirstTypeOfBaseGame> loadGameObjectsUrls() {
        List<String> urls = new ArrayList<>();
        List<FirstTypeOfBaseGame> games = new ArrayList<>();
        try {
            JSONObject file = new JSONObject(LoadJSON.loadJsonGames(this));
            JSONArray gamesJSon = file.getJSONArray("games");
            for (int i = 0; i <  gamesJSon.length(); i++) {
                JSONObject jsonObject = gamesJSon.getJSONObject(i);
                if(jsonObject.getInt("gametype") == 0 ){
                    games.add(new FirstTypeOfBaseGame(jsonObject.getInt("gametype"),
                            jsonObject.getString("word"),
                            jsonObject.getString("image1"),
                            jsonObject.getString("image2"),
                            jsonObject.getString("answer")));

                    urls.add(jsonObject.getString("image1"));
                    urls.add(jsonObject.getString("image2"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return games;
    }

    @Override
    protected void setUp() {
        List<FirstTypeOfBaseGame> games = loadGameObjectsUrls();
        for (int i = 0; i < games.size(); i++) {
            new ImageHandler("game" + i + "_image0.png",null,null,this).execute(games.get(i).getImage0());
            new ImageHandler("game" + i + "_image1.png",null,null,this).execute(games.get(i).getImage1());
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void showGameFragment() {
        FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_game, GameFragment.newInstance(), GameFragment.TAG);
        transaction.commit();
    }

    @Override
    public void processFinish(String output) {
        counter++;
        LogUtil.log(output);
        File  file = new File(output);
        if(file.isFile()){
            file.delete();
            LogUtil.log("delete was successfully.");
        }
        LogUtil.log("" + counter);
        if(counter == 10){
            LogUtil.log("all photos were downloaded.");
        }
    }

}
