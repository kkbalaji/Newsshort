package nri.startup.inshort;


import android.app.Application;
import android.support.annotation.VisibleForTesting;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import nri.startup.inshort.di.component.AppComponent;
import nri.startup.inshort.di.component.DaggerAppComponent;
import nri.startup.inshort.di.module.ApplicationModule;
import nri.startup.inshort.ui.activity.BaseActivity;


/**
 * Created by Krushnakant Solanki on 17-02-2017.
 */


public class App extends Application {
    private static App instance;
    private final AtomicInteger refCount = new AtomicInteger();
    private AppComponent mAppComponent;
    private BaseActivity mActivity;

    public static App getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //   Fabric.with(this, new Crashlytics());
//initialize Realm
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("newsshort.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        mAppComponent = DaggerAppComponent.builder().applicationModule(new ApplicationModule(this))
                .build();
        instance = this;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @VisibleForTesting
    public void setAppComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
    }


}
