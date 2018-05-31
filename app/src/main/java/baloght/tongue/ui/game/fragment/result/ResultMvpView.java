package baloght.tongue.ui.game.fragment.result;

import android.view.View;

import baloght.tongue.ui.base.MvpView;

public interface ResultMvpView extends MvpView, View.OnClickListener{
    void startNewGame();
    void goToHomeMenu();
}
