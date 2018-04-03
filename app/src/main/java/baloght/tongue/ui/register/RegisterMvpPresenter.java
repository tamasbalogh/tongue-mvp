package baloght.tongue.ui.register;

import android.widget.EditText;

import baloght.tongue.di.PerActivity;
import baloght.tongue.ui.base.MvpPresenter;

/**
 * Created by Balogh Tamas on 2018. 04. 01..
 */

@PerActivity
public interface RegisterMvpPresenter<V extends RegisterMvpView> extends MvpPresenter<V> {
    void register(EditText email, EditText user, EditText password);
}
