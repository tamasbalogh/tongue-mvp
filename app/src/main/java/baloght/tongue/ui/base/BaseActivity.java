package baloght.tongue.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import baloght.tongue.R;
import baloght.tongue.Tongue;
import baloght.tongue.di.component.ActivityComponent;
import baloght.tongue.di.component.DaggerActivityComponent;
import baloght.tongue.di.module.ActivityModule;
import baloght.tongue.ui.login.LoginActivity;
import baloght.tongue.utils.KeyboardUtils;
import baloght.tongue.utils.NetworkUtils;
import baloght.tongue.utils.ProgressBarUtils;

/**
 * Created by Balogh Tamas on 2018. 04. 01..
 */

public abstract class BaseActivity extends Activity
        implements MvpView {

    private ProgressBar progressBar;
    private ActivityComponent activityComponent;

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule(this))
                .applicationComponent(((Tongue) getApplication()).getComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    public void showLoading() {
        if(this.progressBar != null){
            ProgressBarUtils.showProgressBar(this.progressBar);
        }
    }

    @Override
    public void hideLoading() {
        if(this.progressBar != null && this.progressBar.isShown()){
            ProgressBarUtils.hideProgressBar(this.progressBar);
        }
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Some Error Occurd", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtils.HideKeyboard(this);
    }


    @Override
    public void openActivityOnTokenExpire() {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    @Override
    public void warnUser(TextView textView) {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        textView.startAnimation(shake);
        Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
        textView.requestFocus();
        KeyboardUtils.showKeyboard(textView, this);
    }
}