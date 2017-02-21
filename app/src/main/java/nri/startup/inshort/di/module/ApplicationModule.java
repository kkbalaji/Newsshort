package nri.startup.inshort.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import nri.startup.inshort.App;
import nri.startup.inshort.networkprocess.NetworkCheck;
import nri.startup.inshort.utils.SharedPreferenceUtils;
import nri.startup.inshort.utils.Utils;

/**
 * Created by Krushnakant Solanki on 17-02-2017.
 */
@Module
public class
ApplicationModule {
    private App mApp;

    public ApplicationModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public Context appContext() {
        return mApp;
    }

    @Provides
    @Singleton
    public Utils utils() {
        return new Utils(mApp);
    }

    @Provides
    @Singleton
    public NetworkCheck networkCheck() {
        return new NetworkCheck(mApp);
    }

    @Provides
    @Singleton
    public Realm realm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    public Gson getGson() {
        GsonBuilder sGsonBuilder = new GsonBuilder();
        return sGsonBuilder.create();
    }

    @Provides
    @Singleton
    public SharedPreferenceUtils getSharedPreference() {
        return new SharedPreferenceUtils(mApp);
    }


}
