package polytechnice.ihm.projet;

import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**
 * Created by anthony LOROSCIO on 04/04/2016.
 */
public class LandingPageFragment extends ListFragment {

    private String htmlText = "<html><body style=\"text-align:justify\"> <style type=\"text/css\">body{color: #fff;} </style> %s </body></Html>";
    private String appIntroduction = "Bienvenue sur l'application Poly'News. Celle-ci vous permets de consulter l'actualité de l'école grace aux articles écrits par nos meilleurs rédacteurs, ou bien grace au flux twitter de l'école.";

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

        WebView webView = (WebView) v.findViewById(R.id.presentationText);
        webView.loadDataWithBaseURL(null, String.format(htmlText, appIntroduction), "text/html", "utf-8", null);
        webView.setBackgroundColor(Color.TRANSPARENT);

        return v;

    }

}
