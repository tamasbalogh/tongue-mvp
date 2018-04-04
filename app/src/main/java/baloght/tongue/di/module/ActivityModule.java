package baloght.tongue.di.module;

import android.app.Activity;
import android.content.Context;

import baloght.tongue.di.ActivityContext;
import baloght.tongue.di.PerActivity;
import baloght.tongue.ui.login.LoginMvpPresenter;
import baloght.tongue.ui.login.LoginMvpView;
import baloght.tongue.ui.login.LoginPresenter;
import baloght.tongue.ui.main.MainActivity;
import baloght.tongue.ui.main.MainMvpPresenter;
import baloght.tongue.ui.main.MainMvpView;
import baloght.tongue.ui.main.MainPresenter;
import baloght.tongue.ui.register.RegisterMvpPresenter;
import baloght.tongue.ui.register.RegisterMvpView;
import baloght.tongue.ui.register.RegisterPresenter;
import baloght.tongue.ui.splash.SplashMvpPresenter;
import baloght.tongue.ui.splash.SplashMvpView;
import baloght.tongue.ui.splash.SplashPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Balogh Tamas on 2018. 04. 01..
 */


@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext(){
        return activity;
    }

    @Provides
    Activity provideActivity(){
        return activity;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(LoginPresenter<LoginMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    RegisterMvpPresenter<RegisterMvpView> provideRegisterPresenter(RegisterPresenter<RegisterMvpView> presenter){
        return  presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter (MainPresenter<MainMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter (SplashPresenter<SplashMvpView> presenter){
        return presenter;
    }
}
