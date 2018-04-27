package baloght.tongue.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import baloght.tongue.di.ActivityContext;
import baloght.tongue.di.PerActivity;
import baloght.tongue.ui.fragment.home.HomeMvpPresenter;
import baloght.tongue.ui.fragment.home.HomeMvpView;
import baloght.tongue.ui.fragment.home.HomePresenter;
import baloght.tongue.ui.fragment.statistics.StatisticsMvpPresenter;
import baloght.tongue.ui.fragment.statistics.StatisticsMvpView;
import baloght.tongue.ui.fragment.statistics.StatisticsPresenter;
import baloght.tongue.ui.game.activity.GameMvpPresenter;
import baloght.tongue.ui.game.activity.GameMvpView;
import baloght.tongue.ui.game.activity.GamePresenter;
import baloght.tongue.ui.game.fragment.GameFMvpPresenter;
import baloght.tongue.ui.game.fragment.GameFMvpView;
import baloght.tongue.ui.game.fragment.GameFPresenter;
import baloght.tongue.ui.login.LoginMvpPresenter;
import baloght.tongue.ui.login.LoginMvpView;
import baloght.tongue.ui.login.LoginPresenter;
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

    public ActivityModule(AppCompatActivity activity) {
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

    @Provides
    @PerActivity
    GameMvpPresenter<GameMvpView> provideGamePresenter (GamePresenter<GameMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    HomeMvpPresenter<HomeMvpView> provideHomePresenter (HomePresenter<HomeMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    StatisticsMvpPresenter<StatisticsMvpView> provideStatisticsPresenter (StatisticsPresenter<StatisticsMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    GameFMvpPresenter<GameFMvpView> provideGameFPresenter (GameFPresenter<GameFMvpView> presenter){
        return presenter;
    }
}
