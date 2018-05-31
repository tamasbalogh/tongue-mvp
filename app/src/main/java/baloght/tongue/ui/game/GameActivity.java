package baloght.tongue.ui.game;

import android.app.FragmentManager;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.ui.game.fragment.desertgame.DesertFragment;
import baloght.tongue.ui.game.fragment.result.ResultFragment;
import baloght.tongue.ui.main.MainActivity;
import baloght.tongue.utils.Constants;
import baloght.tongue.utils.GameUtils.BaseGame;
import baloght.tongue.ui.base.BaseActivity;
import baloght.tongue.utils.GameUtils.Image;
import baloght.tongue.utils.ImageHandler;
import baloght.tongue.utils.LoadJSON;
import baloght.tongue.utils.LogUtil;
import baloght.tongue.utils.ProgressBarUtils;

public class GameActivity extends BaseActivity implements GameMvpView {

    @Inject
    GameMvpPresenter<GameMvpView> presenter;

    FragmentManager manager = getFragmentManager();

    private ProgressBar progressBar;
    private List<BaseGame> games;
    private int downloadedImageCounter = 0;
    private int gameCounter = 0;
    private int numberOfCorrectAnswer = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getActivityComponent().inject(this);
        presenter.onAttach(GameActivity.this);

        if (savedInstanceState != null){
            games = savedInstanceState.getParcelableArrayList("games");
            downloadedImageCounter = savedInstanceState.getInt("downloadedImageCounter");
            gameCounter = savedInstanceState.getInt("gameCounter");
            numberOfCorrectAnswer = savedInstanceState.getInt("numberOfCorrectAnswer");

            if(games.size()-1 == gameCounter){
                float percentage = (numberOfCorrectAnswer * 100.0f) / games.size();
                LogUtil.log("the result as percentage: " + percentage);
                showResultFragment( (int) percentage);
            } else {
                showGameFragment(games.get(gameCounter));
            }
        } else {
            setUp();
        }
    }

    @Override
    protected void setUp() {

        progressBar = findViewById(R.id.gameActivityProgressBar);
        games = loadGameObjects();
        deleteImages();
        if (games != null){
            ProgressBarUtils.showProgressBar(progressBar);
            for (int i = 0; i < games.size(); i++) {
                LogUtil.log(games.get(i).toString());
                new ImageHandler("game" + i + "image0.png", this).execute(games.get(i).getImage0().getUri());
                games.get(i).setImage0(new Image(0,getPathOfImageDir() + "/game" + i + "image0.png"));
                new ImageHandler("game" + i + "image1.png", this).execute(games.get(i).getImage1().getUri());
                games.get(i).setImage1(new Image(1,getPathOfImageDir() + "/game" + i + "image1.png"));
            }
        } else {
            showMessage("Problem occurd, please check network.");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("games",(ArrayList<BaseGame>) games);
        outState.putInt("downloadedImageCounter", downloadedImageCounter);
        outState.putInt("gameCounter", gameCounter);
        outState.putInt("numberOfCorrectAnswer", numberOfCorrectAnswer);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void showGameFragment(BaseGame games) {
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_game, DesertFragment.newInstance(games), DesertFragment.TAG);
        transaction.commit();
    }

    @Override
    public void showResultFragment(int percentage) {
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_game, ResultFragment.newInstance(percentage), ResultFragment.TAG);
        transaction.commit();
    }

    private List<BaseGame> loadGameObjects() {
        List<BaseGame> games = new ArrayList<>();
        try {
            JSONObject file = new JSONObject(LoadJSON.loadJsonObjectFromFile(this));
            JSONArray gamesJSon = file.getJSONArray("games");
            for (int i = 0; i <  gamesJSon.length(); i++) {
                JSONObject jsonObject = gamesJSon.getJSONObject(i);
                if(jsonObject.getInt("gametype") == 0 ){
                    games.add(new BaseGame(jsonObject.getInt("gametype"),
                            jsonObject.getString("word"),
                            new Image(jsonObject.getJSONObject("image0").getInt("id"),
                                    jsonObject.getJSONObject("image0").getString("uri")),
                            new Image(jsonObject.getJSONObject("image1").getInt("id"),
                                    jsonObject.getJSONObject("image1").getString("uri")),
                            jsonObject.getInt("answer")));
                }
                LogUtil.log(games.get(i).toString());
            }
        } catch (JSONException e) {
            LogUtil.log("JSON exception in GameActivity");
            e.printStackTrace();
        }
        return games;
    }

    private String getPathOfImageDir(){
        File dir = getDir(Constants.NAME_OF_IMAGE_FOLDER,MODE_PRIVATE);
        return dir.getPath();
    }

    private void deleteImages(){
        ContextWrapper cw = new ContextWrapper(this);
        File [] images = cw.getDir(Constants.NAME_OF_IMAGE_FOLDER, MODE_PRIVATE).listFiles();
        if (images.length > 0){
            for (File f: images) {
                if(f.getName().contains("game") && f.getAbsoluteFile().delete()){
                    LogUtil.log(f.getName() + " deleted");
                }
            }
        }
    }

    @Override
    public void processFinish(String output) {
        downloadedImageCounter++;
        LogUtil.log(downloadedImageCounter + ". Process finish: " + output);

        if (getNumberOfRequiredImages() == downloadedImageCounter){
            LogUtil.log("show game fragment ...");
            ProgressBarUtils.hideProgressBar(progressBar);
            showGameFragment(games.get(gameCounter));
        }
    }

    private int getNumberOfRequiredImages() {
        int counter = 0;
        for (BaseGame game : games) {
            switch (game.getGametype()){
                case 0:
                    counter+=2;
                    break;
                case 1:
                    counter++;
                    break;
                case 2:
                    counter++;
                    break;
            }
        }
        return counter;
    }

    public void nextGame(int result){
        LogUtil.log("nextGame method - gamecounter: " + (gameCounter+1) + "/" + (games.size()));
        if(games.get(gameCounter).getAnswer() == result){
            numberOfCorrectAnswer++;
            LogUtil.log("Correct hit increased, current: " + numberOfCorrectAnswer);
        }

        if(games.size()-1 == gameCounter){
            float percentage = (numberOfCorrectAnswer * 100.0f) / games.size();
            LogUtil.log("the result as percentage: " + percentage);
            showResultFragment( (int) percentage);
        } else {
            gameCounter++;
            showGameFragment(games.get(gameCounter));
        }
    }
}
