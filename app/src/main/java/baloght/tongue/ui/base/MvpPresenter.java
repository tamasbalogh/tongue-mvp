package baloght.tongue.ui.base;

/**
 * Created by Balogh Tamas on 2018. 04. 01..
 */

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);
    void onDetach();
    void setUserAsLoggedOut();
}
