package polytechnice.ihm.projet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import polytechnice.ihm.projet.Actualities.Article;
import polytechnice.ihm.projet.Actualities.NewsCustomAdapter;
import polytechnice.ihm.projet.Actualities.NewsDBHelper;
import polytechnice.ihm.projet.Actualities.NewsListFragment;


/**
 * Created by Patrice Camousseigt on 16/03/16.
 */
public class FirstFragment extends Fragment {
    NewsCustomAdapter newsCustomAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

            newsCustomAdapter = new NewsCustomAdapter(getContext(), R.id.newsList, articles);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        View rootView = inflater.inflate(R.layout.fragment_news_list, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.newsList);
        gridView.setAdapter(newsCustomAdapter);

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