package nri.startup.inshort.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import nri.startup.inshort.ui.fragment.TutorialFragment;

/**
 * Created by Krushnakant on 2/19/2017.
 */

public class TutorialAdapter extends FragmentPagerAdapter {

    public TutorialAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return TutorialFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

}
