package nri.startup.inshort.ui.activity;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import nri.startup.inshort.R;
import nri.startup.inshort.contractor.NewsContractor;
import nri.startup.inshort.customview.TransformerItem;
import nri.startup.inshort.customview.VerticalDepthPageTransformer;
import nri.startup.inshort.customview.VerticalViewPager;
import nri.startup.inshort.models.NewsModel;
import nri.startup.inshort.presenter.NewsPresenter;
import nri.startup.inshort.ui.adapter.NewsAdapter;
import nri.startup.inshort.utils.Constant;


public class MainActivity extends BaseActivity implements NewsContractor.INewsView {

    @Inject
    NewsPresenter mPresenter;
    private VerticalViewPager verticalViewPager;
    private ArrayList<NewsModel> newsModelArrayList;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        mPresenter.setNewsView(this);

        RealmResults<NewsModel> newsData = mRealm.where(NewsModel.class).findAll();
        newsModelArrayList = (ArrayList<NewsModel>) mRealm.copyFromRealm(newsData);
        setTitle("");
        initViewPager();
    }

    private void initViewPager() {
        verticalViewPager = (VerticalViewPager) findViewById(R.id.vertical_viewpager);
        newsAdapter = new NewsAdapter(getSupportFragmentManager(), newsModelArrayList);
        verticalViewPager.setAdapter(newsAdapter);
        try {
            verticalViewPager.setPageTransformer(true, new TransformerItem(VerticalDepthPageTransformer.class).clazz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        verticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (newsModelArrayList.size() - 3 == position) {
                    if (mPreference.getInteger(Constant.PREF_TOTAL) > mPreference.getInteger(Constant.PREF_NEWS_COUNT))
                        mPresenter.getNewsData(getString(R.string.deviceId), mPreference.getInteger(Constant.PREF_CURRENT_PAGE) + 1);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void setNewsData(final ArrayList<NewsModel> newsListt) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(newsListt);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                newsModelArrayList.addAll(newsListt);
                newsAdapter.notifyDataSetChanged();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e("Error", "Error while inserting to Realm");
            }
        });

    }
}

