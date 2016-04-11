package polytechnice.ihm.projet.Actualities;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import polytechnice.ihm.projet.FirstFragment;
import polytechnice.ihm.projet.R;
import polytechnice.ihm.projet.SecondFragment;
import polytechnice.ihm.projet.ThirdFragment;

/**
 * @author Patrice Camousseigt
 */
public class ArticleView extends Fragment {

    private static final String TITLE = "TITLE";
    private static final String URL = "URL";
    private static final String CONTENT = "CONTENT";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.article_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        TextView title = (TextView) view.findViewById(R.id.title_article);
        TextView content = (TextView) view.findViewById(R.id.content_article);

        if (getArguments() != null) {
            Bundle args = getArguments();
            if (args.containsKey(TITLE)) {

                /* Image */
                String mediaPath = args.getString(URL);
                if (mediaPath.contains("youtube")) {
                    mediaPath = toYoutubePreview(mediaPath);
                }
                Picasso.with(getContext())
                        .load(mediaPath)
                        .into(image);

                /* Title */
                title.setText(args.getString(TITLE));

                /* Content */
                content.setText(args.getString(CONTENT));
            }
            else
                title.setText("ERREUR LECTURE TITRE");
        }
        else
            System.out.println("getArguments est NULL");

    }


    public static ArticleView newInstance(Article article) {
        ArticleView fragment = new ArticleView();
        Bundle args = new Bundle();
        args.putString(TITLE, article.getTitle());
        args.putString(URL, article.getUrl());
        args.putString(CONTENT, article.getContent());
        fragment.setArguments(args);
        return fragment;
    }

    private String toYoutubePreview(String link) {
        return "http://i1.ytimg.com/vi/" + link.split("=")[1] + "/default.jpg";
    }

}
