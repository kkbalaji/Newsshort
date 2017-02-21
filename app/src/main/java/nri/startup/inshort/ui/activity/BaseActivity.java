package nri.startup.inshort.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.realm.Realm;
import nri.startup.inshort.App;
import nri.startup.inshort.di.component.ActivityComponent;
import nri.startup.inshort.di.component.DaggerActivityComponent;
import nri.startup.inshort.networkprocess.NetworkCheck;
import nri.startup.inshort.utils.SharedPreferenceUtils;
import nri.startup.inshort.utils.Utils;


/**
 * Created by Krushnakant Solanki on 29-12-2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    public Utils mUtils;
    @Inject
    public SharedPreferenceUtils mPreference;
    @Inject
    public Gson mGson;
    @Inject
    public NetworkCheck mNetworkCheck;
    protected Toolbar toolbar;
    protected Fragment currentFragment;
    @Inject
    Realm mRealm;
    private Handler handler;
    private boolean bounded;

    private ActivityComponent mActivityComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(getContentResId());
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(getApp().getAppComponent()).build();

        mActivityComponent.inject(this);
        //ButterKnife.bind(this);

    }

    public boolean isNetworkAvailable() {
        return mUtils.isNetworkAvailable(this);
    }

    public App getApp() {
        return (App) getApplicationContext();
    }

    protected ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


//    protected abstract int getContentResId();


}
