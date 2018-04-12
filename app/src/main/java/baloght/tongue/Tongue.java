package baloght.tongue;

import android.app.Application;
import javax.inject.Inject;
import baloght.tongue.data.DataManager;
import baloght.tongue.di.component.ApplicationComponent;
import baloght.tongue.di.component.DaggerApplicationComponent;
import baloght.tongue.di.module.ApplicationModule;

/**
 * Created by Balogh Tamas on 2018. 04. 01..
 */

public class Tongue extends Application {

    @Inject
    DataManager dataManager;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return this.applicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
