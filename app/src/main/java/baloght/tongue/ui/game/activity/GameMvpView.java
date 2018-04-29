package baloght.tongue.ui.game.activity;


import baloght.tongue.ui.base.MvpView;
import baloght.tongue.utils.ImageHandler;


public interface GameMvpView extends MvpView, ImageHandler.AsyncResponse {
    void showGameFragment();
}
