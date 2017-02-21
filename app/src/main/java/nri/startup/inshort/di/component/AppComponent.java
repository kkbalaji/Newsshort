package nri.startup.inshort.di.component;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import nri.startup.inshort.di.module.ApplicationModule;
import nri.startup.inshort.networkprocess.ApiModule;
import nri.startup.inshort.networkprocess.NetworkCheck;
import nri.startup.inshort.networkprocess.WebService;
import nri.startup.inshort.ui.fragment.BaseFragment;
import nri.startup.inshort.utils.SharedPreferenceUtils;
import nri.startup.inshort.utils.Utils;

/**
 * Created by Krushnakant Solanki on 17-02-2017.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface AppComponent {

    WebService apiService();

    NetworkCheck networkCheck();

    Realm realm();

    Utils utils();

    Context appContext();

    Gson getGson();

    SharedPreferenceUtils getSharedPreference();


    void inject(BaseFragment baseFragment);


}
