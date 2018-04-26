package baloght.tongue.ui.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import baloght.tongue.R;
import baloght.tongue.di.component.ActivityComponent;

/**
 * Created by baloght on 2018.04.11..
 */

public abstract class  BaseFragment extends Fragment implements MvpView{

    private BaseActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.activity = activity;
            activity.onFragmentAttached();
        }
    }

    public ActivityComponent getActivityComponent() {
        if (activity != null) {
            return activity.getActivityComponent();
        }
        return null;
    }

    @Override
    public void showMessage(String message) {
        if(activity != null){
            activity.showMessage(message);
        }
    }

    @Override
    public void onDetach() {
        activity = null;
        super.onDetach();
    }

    @Override
    public void openActivityOnTokenExpire() {
        if (activity != null){
            activity.openActivityOnTokenExpire();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (activity != null){
            return activity.isNetworkConnected();
        }
        return  false;
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

    protected abstract void setUp(View view);

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void hideKeyboard() {

    }


    @Override
    public void warnUser(TextView textView) {

    }
}
