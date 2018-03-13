package baloght.tongue.register;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import baloght.tongue.R;

/**
 * Created by Balogh Tamas on 2018. 03. 12..
 */

public class RegisterView extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
