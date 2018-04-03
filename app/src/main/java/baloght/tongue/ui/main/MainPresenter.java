package baloght.tongue.ui.main;

import baloght.tongue.data.DataManager;
import baloght.tongue.ui.base.BasePresenter;

/**
 * Created by Balogh Tamas on 2018. 04. 02..
 */

public class MainPresenter <V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
