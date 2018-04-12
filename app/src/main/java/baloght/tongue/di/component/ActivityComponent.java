package baloght.tongue.di.component;


import baloght.tongue.di.PerActivity;
import baloght.tongue.di.module.ActivityModule;
import baloght.tongue.ui.fragment.home.HomeFragment;
import baloght.tongue.ui.game.GameActivity;
import baloght.tongue.ui.login.LoginActivity;
import baloght.tongue.ui.main.MainActivity;
import baloght.tongue.ui.register.RegisterActivity;
import baloght.tongue.ui.splash.SplashActivity;
import dagger.Component;

/**
 * Created by Balogh Tamas on 2018. 04. 01..
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

     void inject(LoginActivity activity);
     void inject(RegisterActivity activity);
     void inject(MainActivity activity);
     void inject(SplashActivity activity);
     void inject(GameActivity activity);
     void inject(HomeFragment fragment);
}
