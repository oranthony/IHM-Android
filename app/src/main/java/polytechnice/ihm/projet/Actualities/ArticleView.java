package polytechnice.ihm.projet.Actualities;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import polytechnice.ihm.projet.FirstFragment;
import polytechnice.ihm.projet.R;
import polytechnice.ihm.projet.SecondFragment;
import polytechnice.ihm.projet.ThirdFragment;

/**
 * @author Patrice Camousseigt
 */
public class ArticleView extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.article_view, container, false);
        ImageView image = (ImageView) rootView.findViewById(R.id.imageView);
        image.setImageResource(R.drawable.image_article);

        TextView text = (TextView) rootView.findViewById(R.id.text_article);
        text.setText("IT WORKS !");

        //GridView gridView = (GridView) rootView.findViewById(R.id.newsList);
        //gridView.setAdapter(newsCustomAdapter);

        return rootView;
    }

    public static ArticleView newInstance() {
        ArticleView fragment = new ArticleView();
        return fragment;
    }


}
