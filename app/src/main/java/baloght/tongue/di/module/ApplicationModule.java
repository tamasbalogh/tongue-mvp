package baloght.tongue.di.module;

import android.app.Application;
import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;

import javax.inject.Singleton;

import baloght.tongue.data.DataManager;
import baloght.tongue.data.DataManagerImpl;
import baloght.tongue.data.image.ImageHandler;
import baloght.tongue.data.network.ApiHelper;
import baloght.tongue.data.network.ApiHelperImpl;
import baloght.tongue.data.prefs.PreferencesHelper;
import baloght.tongue.data.prefs.PreferencesHelperImpl;
import baloght.tongue.di.ApplicationContext;
import baloght.tongue.di.AsyncClient;
import baloght.tongue.di.PreferenceName;
import baloght.tongue.utils.Constants;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Balogh Tamas on 2018. 04. 01..
 */

@Module
public class ApplicationModule {

    public final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @PreferenceName
    String providePreferenceName(){
        return Constants.PREF_NAME;
    }

    @Provides
    @AsyncClient
    AsyncHttpClient provideAsyncHttpClient(){
        return new AsyncHttpClient();
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImpl dataManagerImpl){
        return dataManagerImpl;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(PreferencesHelperImpl preferencesHelperImpl) {
        return preferencesHelperImpl;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(ApiHelperImpl apiHelperImpl){
        return apiHelperImpl;
    }

    @Provides
    @Singleton
    ImageHandler provideImageHandler(ImageHandler imageHandler){
        return imageHandler;
    }
}
