package polytechnice.ihm.projet;

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

    //TODO Rename view like #Polytech'Nice => understand that the 2 views are different => Mur de tweet

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
