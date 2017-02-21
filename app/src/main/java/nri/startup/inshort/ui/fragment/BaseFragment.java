package nri.startup.inshort.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.google.gson.Gson;

import javax.inject.Inject;

import nri.startup.inshort.App;
import nri.startup.inshort.di.component.AppComponent;
import nri.startup.inshort.networkprocess.WebService;
import nri.startup.inshort.utils.SharedPreferenceUtils;
import nri.startup.inshort.utils.Utils;

/**
 * Created by Krushnakant on 27-07-2016.
 */
public abstract class BaseFragment extends Fragment {
    @Inject
    public Utils mUtils;

    @Inject
    public Gson mGson;

    @Inject
    public SharedPreferenceUtils mPreference;

    @Inject
    public WebService mService;

    private AppComponent mAppComponent;

    protected AppComponent getAppComponent() {
        return mAppComponent;
    }

    protected App getApp() {
        return (App) getActivity().getApplicationContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppComponent = getApp().getAppComponent();
        mAppComponent.inject(this);
    }


    public int getNumber(CharSequence string) {
        int number = 0;
        if (!mUtils.isEmpty(string) && TextUtils.isDigitsOnly(string)) {
            number = Integer.parseInt(string.toString());
        }
        return number;
    }
}
