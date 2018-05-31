package baloght.tongue.ui.game;


import java.util.List;
import baloght.tongue.ui.base.MvpView;
import baloght.tongue.utils.GameUtils.BaseGame;
import baloght.tongue.utils.ImageHandler;


public interface GameMvpView extends MvpView, ImageHandler.AsyncResponse {
    void showGameFragment(BaseGame games);
    void showResultFragment(int percentage);
}
