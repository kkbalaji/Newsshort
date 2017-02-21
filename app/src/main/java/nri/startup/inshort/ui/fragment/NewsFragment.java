package nri.startup.inshort.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import nri.startup.inshort.R;
import nri.startup.inshort.models.NewsModel;

/**
 * Created by Krushnakant on 2/19/2017.
 */

public class NewsFragment extends BaseFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_NEWMODEL = "newsmodel";

    @BindView(R.id.newsimage)
    ImageView newsimage;
    @BindView(R.id.newsTitle)
    TextView newsTitle;
    @BindView(R.id.newsDesc)
    TextView newsDesc;

    public NewsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static NewsFragment newInstance(int sectionNumber, NewsModel newsModel) {
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NEWMODEL, newsModel);
        bundle.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int no = getArguments().getInt(ARG_SECTION_NUMBER);
        NewsModel newsModel = getArguments().getParcelable(ARG_NEWMODEL);

        Glide.with(this)
                .load(newsModel.getImage())
                .placeholder(R.drawable.ic_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(newsimage);
        newsTitle.setText(newsModel.getTitle());
        newsDesc.setText(newsModel.getDescription());

    }

}
