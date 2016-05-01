package polytechnice.ihm.PolyNews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import polytechnice.ihm.PolyNews.Actualities.Article;
import polytechnice.ihm.PolyNews.Actualities.ArticleView;
import polytechnice.ihm.PolyNews.Actualities.NewsCustomAdapter;
import polytechnice.ihm.PolyNews.Actualities.NewsDBHelper;


/**
 * Creates the view of the second fragment Actualities
 * @author Patrice Camousseigt
 */
public class ActualitiesFragment extends Fragment {

    private NewsCustomAdapter newsCustomAdapterHead;
    private NewsCustomAdapter newsCustomAdapter;

    private static final int ORIENTATION_PORTRAIT = 1;
    private static final int ORIENTATION_LANDSCAPE = 2;

    /**
     * Creates the view of article from a database
     * @param inflater the inflater
     * @param container the container
     * @param savedInstanceState the saved instances
     * @return the view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        NewsDBHelper db = null;

        try {
            db = new NewsDBHelper(getContext());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        try {
            db.createDataBase();
            db.openDataBase();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        List<Article> articles = db.readDB();

        final View rootView;

        int surfaceOrientation = getResources().getConfiguration().orientation;

        if (surfaceOrientation == ORIENTATION_PORTRAIT) {

            rootView = inflater.inflate(R.layout.fragment_news_list_portrait, container, false);

            List<Article> articleHead = articles.subList(0, 1);
            List<Article> articlesNew = articles.subList(1, articles.size());

            newsCustomAdapterHead = new NewsCustomAdapter(getContext(), R.id.head_articles, articleHead);
            newsCustomAdapter = new NewsCustomAdapter(getContext(), R.id.newsList, articlesNew);

            final GridView gridViewHead = (GridView) rootView.findViewById(R.id.head_articles);
            gridViewHead.setAdapter(newsCustomAdapterHead);

            gridViewHead.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    ArticleView fragmentClass = ArticleView.newInstance((Article) gridViewHead.getItemAtPosition(position));

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_container, fragmentClass).commit();
                }
            });
        }
        else { // if ORIENTATION_LANDSCAPE
            rootView = inflater.inflate(R.layout.fragment_news_list_landscape, container, false);
            newsCustomAdapter = new NewsCustomAdapter(getContext(), R.id.newsList, articles);
        }

        final GridView gridView = (GridView) rootView.findViewById(R.id.newsList);
        gridView.setAdapter(newsCustomAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                ArticleView fragmentClass = ArticleView.newInstance((Article) gridView.getItemAtPosition(position));

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_container, fragmentClass).commit();

            }
        });

        return rootView;
    }

    /**
     * Creates a new instance of the Actualities fragment
     * @return
     */
    public static ActualitiesFragment newInstance() {
        ActualitiesFragment fragment = new ActualitiesFragment();
        return fragment;
    }

}