package baloght.tongue.ui.splash;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.ui.base.BaseActivity;
import baloght.tongue.ui.login.LoginActivity;
import baloght.tongue.ui.main.MainActivity;

/**
 * Created by baloght on 2018.04.04..
 */

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //generateKeyHash();

        getActivityComponent().inject(this);
        mPresenter.onAttach(SplashActivity.this);
        setUp();
    }

    private void generateKeyHash(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "baloght.tongue",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void openLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
