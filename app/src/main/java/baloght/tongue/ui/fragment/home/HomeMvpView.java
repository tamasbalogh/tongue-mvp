package baloght.tongue.ui.fragment.home;

import android.graphics.Bitmap;
import android.view.View;

import baloght.tongue.ui.base.MvpView;

/**
 * Created by baloght on 2018.04.12..
 */

public interface HomeMvpView extends MvpView, View.OnClickListener {
    void updateUserName(String userName);
    void updateUserProfilePic(Bitmap bitmap);
    void OpenGameActivity();
}
