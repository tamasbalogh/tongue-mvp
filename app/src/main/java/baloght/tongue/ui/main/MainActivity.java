package baloght.tongue.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import baloght.tongue.R;
import baloght.tongue.ui.base.BaseActivity;

/**
 * Created by Balogh Tamas on 2018. 04. 02..
 */

public class MainActivity extends BaseActivity implements MainMvpView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
