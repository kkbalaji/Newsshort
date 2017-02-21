package nri.startup.inshort.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import nri.startup.inshort.R;

/**
 * Created by Krushnakant on 2/19/2017.
 */

public class TutorialFragment extends BaseFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.swipeup)
    TextView swipeup;

    public TutorialFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TutorialFragment newInstance(int sectionNumber) {
        TutorialFragment fragment = new TutorialFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tutorial, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int no = getArguments().getInt(ARG_SECTION_NUMBER);
        if (no == 0) {
            image.setImageResource(R.mipmap.ic_onboarding_60_words);
            title.setText(getString(R.string.words));
            desc.setText(getString(R.string.words_desc));

        } else if (no == 1 || no == 2) {
            image.setImageResource(R.mipmap.ic_onboarding_personalisation);
            title.setText(getString(R.string.personalised));
            desc.setText(getString(R.string.personalised_desc));

        } else if (no == 2) {
            image.setImageResource(R.mipmap.ic_onboarding_search);
            title.setText(getString(R.string.search));
            desc.setText(getString(R.string.search_desc));

        }

    }


}
