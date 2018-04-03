package baloght.tongue.ui.main;

import baloght.tongue.ui.base.MvpView;

/**
 * Created by Balogh Tamas on 2018. 04. 02..
 */

public interface MainMvpView extends MvpView {
    void openLoginActivity();
    void updateUserName(String userName);
    void updateUserEmail(String userEmail);
    void updateUserProfilePic(String userProfilePicUrl);
}
