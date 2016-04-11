package polytechnice.ihm.projet;

import android.app.Activity;
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

        final View rootView = inflater.inflate(R.layout.activity_main, container, false);
        final GridView gridView = (GridView) rootView.findViewById(R.id.newsList);
        gridView.setAdapter(newsCustomAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                ArticleView fragmentClass = ArticleView.newInstance((Article) gridView.getItemAtPosition(position));

                System.out.println("====== Click. ID : " + id + " POSITION : " + position + " ======");
                System.out.println(" - View : " + v);
                System.out.println(" - ROOTVIEW : " + rootView);


                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_content, fragmentClass).commit();

                // Highlight the selected item has been done by NavigationView
                //menuItem.setChecked(true);

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