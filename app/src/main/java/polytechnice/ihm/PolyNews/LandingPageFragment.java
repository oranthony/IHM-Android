package polytechnice.ihm.PolyNews;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**
 * @author Anthony Loroscio
 * This is the landing page which introduce the application and displays the school official tweets
 */
public class LandingPageFragment extends ListFragment {

    //Sliding panel layout
    private SlidingUpPanelLayout  slider;

    //Orientation variables
    private static final int ORIENTATION_PORTRAIT = 1;
    private static final int ORIENTATION_LANDSCAPE = 2;

    private View v;

    private ListView menuListView;

    //This is the introducing text. It's an HTML text with some css to justify it.
    private String htmlText = "<html><body> <style type=\"text/css\">body{color: #fff; font-size: " +
            "0.9em; text-align:justify;} </style> %s </body></Html>";
    private String appIntroduction = "Bienvenue sur l'application Poly'News. Celle-ci vous permet de " +
            "consulter l'actualité de l'école grâce aux articles et au flux twitter de l'école.";

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        //We get the timeline of the official polytechnice twitter account, so we only have the
        //tweets from polytechnice twitter account
        UserTimeline userTimeline = new UserTimeline.Builder().screenName("polytechnice").build();

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity())
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int surfaceOrientation = getResources().getConfiguration().orientation;

        if (surfaceOrientation == ORIENTATION_PORTRAIT) {
            v = inflater.inflate(R.layout.landing_page_portrait, container, false);
        }else { //Landscape
            v = inflater.inflate(R.layout.landing_page_landscape, container, false);
        }

        slider = (SlidingUpPanelLayout)v.findViewById(R.id.sliding_layout);

        WebView webView = (WebView) v.findViewById(R.id.presentationText);
        webView.loadDataWithBaseURL(null, String.format(htmlText, appIntroduction), "text/html", "utf-8", null);
        webView.setBackgroundColor(Color.TRANSPARENT);

        menuListView = (ListView) v.findViewById(android.R.id.list);

        v.findViewById(R.id.arrowUp).setOnTouchListener( new View.OnTouchListener(){

            //We expand the panel when the little arrow is touched
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                slider.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                return true;
            }

        });

        //We override some functions of the sliding panel framework in order to block the scroll
        //of the twitter list when the panel is collapsed.
        slider.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener(){
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            //Here we block the scroll to make the drag action easier
            @Override
            public void onPanelCollapsed(View panel) {
                // Disable Scrolling by setting up an OnTouchListener to do nothing
                menuListView.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });

            }

            //Here we make the tweet list scrollable again to allow the user to scroll through the tweets
            @Override
            public void onPanelExpanded(View panel) {
                // Enable Scrolling by removing the OnTouchListner
                menuListView.setOnTouchListener(null);
            }

            @Override
            public void onPanelAnchored(View panel) {

            }

            @Override
            public void onPanelHidden(View panel) {

            }
            // Override here
        });

        return v;

    }

}
