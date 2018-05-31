package baloght.tongue.ui.fragment.home;

import android.graphics.Bitmap;
import android.view.View;

import baloght.tongue.data.DataManager;
import baloght.tongue.ui.base.MvpView;
import baloght.tongue.utils.ImageHandler;

/**
 * Created by baloght on 2018.04.12..
 */

public interface HomeMvpView extends MvpView, View.OnClickListener, ImageHandler.AsyncResponse {
    void updateUserName(String userName);
    void updateUserProfilePic(DataManager.LoggedInMode mode, String profilePicUrl, String storedPicPath);
    void OpenGameActivity();
}
