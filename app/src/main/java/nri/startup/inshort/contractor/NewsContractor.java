package nri.startup.inshort.contractor;


import java.util.ArrayList;

import nri.startup.inshort.models.NewsModel;

/**
 * Created by Krushnakant Solanki on 17-02-2017.
 */
public interface NewsContractor {
    interface INewsView {

        void setNewsData(ArrayList<NewsModel> newsListt);

    }

    interface INewsPresenter {
        void getNewsData(String deviceId, int pageNo);

    }
}
