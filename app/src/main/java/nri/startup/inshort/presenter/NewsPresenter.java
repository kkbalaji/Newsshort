package nri.startup.inshort.presenter;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import nri.startup.inshort.contractor.NewsContractor;
import nri.startup.inshort.models.NewsModel;
import nri.startup.inshort.networkprocess.WebService;
import nri.startup.inshort.utils.Constant;
import nri.startup.inshort.utils.SharedPreferenceUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Krushnakant Solanki on 17-02-2017.
 */
public class NewsPresenter implements NewsContractor.INewsPresenter {

    private WebService mWebservice;
    private SharedPreferenceUtils mPreference;
    private Gson mGson;
    private NewsContractor.INewsView newsView;

    @Inject
    public NewsPresenter(SharedPreferenceUtils mPreference, Gson mGson, WebService mWebservice) {
        this.mPreference = mPreference;
        this.mGson = mGson;
        this.mWebservice = mWebservice;
    }

    public void setNewsView(NewsContractor.INewsView newsView) {
        this.newsView = newsView;
    }

    @Override
    public void getNewsData(String deviceId, int pageNo) {

        mWebservice.getNews(deviceId, pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<JsonObject, ArrayList<NewsModel>>() {
                    @Override
                    public ArrayList<NewsModel> call(JsonObject jsonObject) {
                        ArrayList<NewsModel> newsList = new ArrayList<NewsModel>();
                        if (jsonObject != null) {
                            JsonArray newsarray = jsonObject.getAsJsonArray("data");
                            mPreference.storeIntegerValue(Constant.PREF_TOTAL, jsonObject.get("total").getAsInt());
                            mPreference.storeIntegerValue(Constant.PREF_CURRENT_PAGE, jsonObject.get("current_page").getAsInt());
                            mPreference.storeIntegerValue(Constant.PREF_NEWS_COUNT, jsonObject.get("to").getAsInt());

                            if (newsarray != null) {
                                newsList = mGson.fromJson(newsarray, new TypeToken<List<NewsModel>>() {
                                }.getType());
                            }
                        }
                        return newsList;
                    }
                })
                .subscribe(new Subscriber<ArrayList<NewsModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error in Getting News", e != null ? e.getMessage() : "Error");
                    }

                    @Override
                    public void onNext(ArrayList<NewsModel> newsList) {
                        newsView.setNewsData(newsList);
                    }
                });
    }

}
