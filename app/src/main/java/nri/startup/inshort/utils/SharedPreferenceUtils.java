package nri.startup.inshort.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Prashant on 13-04-2016.
 */
public class SharedPreferenceUtils {


    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private Context context;

    public SharedPreferenceUtils(Context contx) {
        context = contx;
        mPref = context.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public void clearAllPreference() {
        mEditor.clear();
        mEditor.commit();
    }


    public void storeStringValue(String Key, String value) {
        mEditor.putString(Key, value);
        mEditor.commit();
    }


    public boolean has(String key) {
        return mPref.contains(key);
    }

    public String getString(String Key) {
        return mPref.getString(Key, "");
    }


    public void storeBooleanValue(String Key, boolean value) {
        mEditor.putBoolean(Key, value);
        mEditor.commit();
    }

    public boolean getBoolean(String Key) {
        return mPref.getBoolean(Key, false);
    }

    public void storeIntegerValue(String Key, int value) {
        mEditor.putInt(Key, value);
        mEditor.commit();
    }

    public int getInteger(String Key) {
        return mPref.getInt(Key, 0);
    }


    public void removeValue(String KEY) {
        mEditor.remove(KEY);
        mEditor.commit();
    }

}
