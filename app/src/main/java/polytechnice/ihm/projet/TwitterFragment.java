package polytechnice.ihm.projet;

import android.app.Fragment;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import io.fabric.sdk.android.Fabric;

/**
 * Created by antho on 28/03/2016.
 */
public class TwitterFragment extends ListFragment{

    //Twitter
    private static final String TWITTER_KEY = "uoS5Row3TjfSQmwiFs7qKrQgj";
    private static final String TWITTER_SECRET = "4VXVrzsPgxfohiqzG5kIZrxCf4B14daEqM9gVToDO0VanjeZdd";

    /*SearchTimeline searchTimeline;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        setSearchTimeline();


    }

    private void setSearchTimeline(){
        searchTimeline = new SearchTimeline.Builder()
                .query("#twitterflock")
                .build();
    }*/

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //setContentView(R.layout.timeline);

        return inflater.inflate(R.layout.timeline, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        UserTimeline userTimeline = new UserTimeline.Builder().screenName("fabric").build();

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity().getApplicationContext())
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);
    }

    /*public static TwitterFragment newInstance() {
        TwitterFragment fragment = new TwitterFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }*/

}
