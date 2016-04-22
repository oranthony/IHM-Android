package polytechnice.ihm.projet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.GestureDetector;
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
 */
public class LandingPageFragment extends ListFragment {

    //Sliding panel layout
    private SlidingUpPanelLayout  slider;

    //Swipe up detector
    private GestureDetector gDetector;

    private ListView _menuListView;

    private String htmlText = "<html><body> <style type=\"text/css\">body{color: #fff; font-size: 0.9em; text-align:justify;} </style> %s </body></Html>";
    private String appIntroduction = "Bienvenue sur l'application Poly'News. Celle-ci vous permet de consulter l'actualité de l'école grâce aux articles et au flux twitter de l'école.";

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        UserTimeline userTimeline = new UserTimeline.Builder().screenName("polytechnice").build();

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity())
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.landing_page, container, false);

        slider = (SlidingUpPanelLayout)v.findViewById(R.id.sliding_layout);

        WebView webView = (WebView) v.findViewById(R.id.presentationText);
        webView.loadDataWithBaseURL(null, String.format(htmlText, appIntroduction), "text/html", "utf-8", null);
        webView.setBackgroundColor(Color.TRANSPARENT);

        _menuListView = (ListView) v.findViewById(android.R.id.list);

        v.findViewById(R.id.arrowUp).setOnTouchListener( new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                slider.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                return true;
            }
        });

        slider.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener(){
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelCollapsed(View panel) {

                _menuListView.setOnTouchListener( new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });

            }

            @Override
            public void onPanelExpanded(View panel) {
                // Enable Scrolling by removing the OnTouchListner
                _menuListView.setOnTouchListener(null);
            }

            @Override
            public void onPanelAnchored(View panel) {

            }

            @Override
            public void onPanelHidden(View panel) {
                // Disable Scrolling by setting up an OnTouchListener to do nothing

            }
            // Override here
        });

        return v;

    }

}
