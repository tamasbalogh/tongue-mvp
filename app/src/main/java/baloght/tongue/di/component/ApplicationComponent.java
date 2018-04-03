package baloght.tongue.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import baloght.tongue.Tongue;
import baloght.tongue.data.DataManager;
import baloght.tongue.di.ApplicationContext;
import baloght.tongue.di.module.ApplicationModule;
import dagger.Component;

/**
 * Created by Balogh Tamas on 2018. 04. 01..
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(Tongue tongue);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
