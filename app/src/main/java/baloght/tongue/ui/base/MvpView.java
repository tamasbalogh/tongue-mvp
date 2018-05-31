package baloght.tongue.ui.base;

import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Balogh Tamas on 2018. 04. 01..
 */

public interface MvpView {

    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    void showMessage(String message);

    boolean isNetworkConnected();

    void hideKeyboard();

    void warnUser(TextView textView);

}
