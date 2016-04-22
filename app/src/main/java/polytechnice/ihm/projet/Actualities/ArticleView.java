package polytechnice.ihm.projet.Actualities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import polytechnice.ihm.projet.R;

/**
 * Displays an article
 * @author Patrice Camousseigt
 */
public class ArticleView extends Fragment {

    private static final String TITLE = "TITLE";
    private static final String URL = "URL";
    private static final String CONTENT = "CONTENT";
    private static final String DATE_AUTHOR = "DATE_AUTHOR";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.article_view, container, false);
    }

    /**
     * Loads the data of the articles
     * @param view the view
     * @param savedInstanceState the savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        TextView title = (TextView) view.findViewById(R.id.title_article);
        TextView dateAuthor = (TextView) view.findViewById(R.id.date_author);
        TextView content = (TextView) view.findViewById(R.id.content_article);

        if (getArguments() != null) {
            Bundle args = getArguments();
            if (args.containsKey(URL)) {

                String mediaPath = args.getString(URL);
                if (mediaPath != null && mediaPath.contains("youtube")) {
                    mediaPath = toYoutubePreview(mediaPath);
                }
                Picasso.with(getContext())
                        .load(mediaPath)
                        .into(image);
            }

            if (args.containsKey(TITLE)) {

                title.setText(args.getString(TITLE));
            }

            if (args.containsKey(CONTENT)) {

                content.setText(args.getString(CONTENT));
            }
            if(args.containsKey(DATE_AUTHOR)){
                dateAuthor.setText(args.getString(DATE_AUTHOR));
            }
        }
        else
            System.out.println("getArguments est NULL");

    }

    /**
     * Create a new instance of ArticleView recovering the data of the article
     * @param article the article
     * @return the fragment created with arguments set
     */
    public static ArticleView newInstance(Article article) {
        ArticleView fragment = new ArticleView();
        Bundle args = new Bundle();
        args.putString(URL, article.getUrl());
        args.putString(DATE_AUTHOR, article.getDate() + " " + article.getAuthor());
        args.putString(TITLE, "[" + article.getCategory() + "] " + article.getTitle());
        args.putString(CONTENT, article.getContent());
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Concatenation of the link
     * @param link the link wanted to be concatenated
     * @return the link concatenated
     */
    private String toYoutubePreview(String link) {
        return "http://i1.ytimg.com/vi/" + link.split("=")[1] + "/default.jpg";
    }

}
