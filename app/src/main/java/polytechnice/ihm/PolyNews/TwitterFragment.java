package polytechnice.ihm.PolyNews;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

/**
 * Created by anthony LOROSCIO on 28/03/2016.
 */
public class TwitterFragment extends ListFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //We do a Timeline base on the keyword "polytechnice" so we get both the tweets from the
        //official account and all the others tweets related to polytechnice
        final SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query("polytechnice")
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity())
                .setTimeline(searchTimeline)
                .build();
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.timeline, container, false);
    }

}
