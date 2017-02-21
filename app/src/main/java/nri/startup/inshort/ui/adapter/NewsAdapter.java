package nri.startup.inshort.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import nri.startup.inshort.models.NewsModel;
import nri.startup.inshort.ui.fragment.NewsFragment;

/**
 * Created by Krushnakant on 17/02/2017.
 */

public class NewsAdapter extends FragmentPagerAdapter {

    ArrayList<NewsModel> newsModelArrayList;

    public NewsAdapter(FragmentManager fm, ArrayList<NewsModel> newsModelArrayList) {
        super(fm);
        this.newsModelArrayList = newsModelArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
//            return NewsFragment.newInstance(position, newsModelArrayList.get(position));
        return NewsFragment.newInstance(position, newsModelArrayList.get(position));

    }

    @Override
    public int getCount() {
        // Show 5 total pages.
        return newsModelArrayList.size();
    }

}


