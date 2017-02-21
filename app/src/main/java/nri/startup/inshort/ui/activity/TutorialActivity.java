package nri.startup.inshort.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;
import nri.startup.inshort.R;
import nri.startup.inshort.contractor.NewsContractor;
import nri.startup.inshort.customview.TransformerItem;
import nri.startup.inshort.customview.VerticalDepthPageTransformer;
import nri.startup.inshort.customview.VerticalViewPager;
import nri.startup.inshort.models.NewsModel;
import nri.startup.inshort.presenter.NewsPresenter;
import nri.startup.inshort.ui.adapter.TutorialAdapter;
import nri.startup.inshort.utils.Constant;


public class TutorialActivity extends BaseActivity implements NewsContractor.INewsView {

    @Inject
    NewsPresenter mPresenter;
    int pageNo;
    private VerticalViewPager verticalViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        if (!mPreference.getBoolean(Constant.PREF_FIRST_TIME)) {
            pageNo = 1;
            if (mNetworkCheck.isConnected()) {
                mPresenter.getNewsData(getString(R.string.deviceId), pageNo);
                mPresenter.setNewsView(this);
                setTitle("");
                initViewPager();
            } else {
                Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            }
        } else {
            Intent main = new Intent(TutorialActivity.this, MainActivity.class);
            startActivity(main);
            finish();

        }
    }

    private void initViewPager() {
        verticalViewPager = (VerticalViewPager) findViewById(R.id.vertical_viewpager);
        verticalViewPager.setAdapter(new TutorialAdapter(getSupportFragmentManager()));
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
                if (position == 2) {
                    mPreference.storeBooleanValue(Constant.PREF_FIRST_TIME, true);
                    Intent main = new Intent(TutorialActivity.this, MainActivity.class);
                    startActivity(main);
                    finish();
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
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e("Error", "Error while inserting to Realm");
            }
        });
    }
}

