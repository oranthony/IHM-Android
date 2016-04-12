package polytechnice.ihm.projet;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**
 * Created by antho on 04/04/2016.
 */
public class LandingPageFragment extends ListFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.landing_page, container, false);
        View view = inflater.inflate(R.layout.landing_page, container, false);
        return view;

    }

    public void onActivityCreated(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        UserTimeline userTimeline = new UserTimeline.Builder().screenName("polytechnice").build();

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity().getApplicationContext())
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);
    }
}
