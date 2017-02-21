package nri.startup.inshort.customview;

import android.support.v4.view.ViewPager;

/**
 * Created by Krushnakant on 2/19/2017.
 */

public final class TransformerItem {

    final public Class<? extends ViewPager.PageTransformer> clazz;
    final String title;

    public TransformerItem(Class<? extends ViewPager.PageTransformer> clazz) {
        this.clazz = clazz;
        title = clazz.getSimpleName();
    }

    @Override
    public String toString() {
        return title;
    }

}
