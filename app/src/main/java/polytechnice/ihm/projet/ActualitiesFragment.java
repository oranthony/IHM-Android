package polytechnice.ihm.projet;

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

import polytechnice.ihm.projet.Actualities.Article;
import polytechnice.ihm.projet.Actualities.ArticleView;
import polytechnice.ihm.projet.Actualities.NewsCustomAdapter;
import polytechnice.ihm.projet.Actualities.NewsDBHelper;


/**
 * Creates the view of the second fragment Actualities
 * @author Patrice Camousseigt
 */
public class ActualitiesFragment extends Fragment {

    private NewsCustomAdapter newsCustomAdapterHead;
    private NewsCustomAdapter newsCustomAdapter;

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

            List<Article> articles = db.readDB();
            List<Article> articleHead = articles.subList(0, 1);
            List<Article> articlesNew = articles.subList(1, articles.size());

            newsCustomAdapterHead = new NewsCustomAdapter(getContext(), R.id.head_articles, articleHead);
            newsCustomAdapter = new NewsCustomAdapter(getContext(), R.id.newsList, articlesNew);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        final View rootView = inflater.inflate(R.layout.fragment_news_list, container, false);

        final GridView gridViewHead = (GridView) rootView.findViewById(R.id.head_articles);
        gridViewHead.setAdapter(newsCustomAdapterHead);

        final GridView gridView = (GridView) rootView.findViewById(R.id.newsList);
        gridView.setAdapter(newsCustomAdapter);

        gridViewHead.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                ArticleView fragmentClass = ArticleView.newInstance((Article) gridViewHead.getItemAtPosition(position));

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_container, fragmentClass).commit();
            }
        });

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