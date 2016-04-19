package polytechnice.ihm.projet;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
 * @author Patrice Camousseigt
 */
public class FirstFragment extends Fragment {

    NewsCustomAdapter newsCustomAdapterHead;
    NewsCustomAdapter newsCustomAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        NewsDBHelper db = null;
        try {
            db = new NewsDBHelper(getContext());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        final GridView gridViewHead = (GridView) rootView.findViewById(R.id.head_articles);
        gridViewHead.setAdapter(newsCustomAdapterHead);

        final GridView gridView = (GridView) rootView.findViewById(R.id.newsList);
        gridView.setAdapter(newsCustomAdapter);


        gridViewHead.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                ArticleView fragmentClass = ArticleView.newInstance((Article) gridViewHead.getItemAtPosition(position));

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_container, fragmentClass).commit();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                ArticleView fragmentClass = ArticleView.newInstance((Article) gridView.getItemAtPosition(position));

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_container, fragmentClass).commit();

            }
        });

        return rootView;
    }

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
}