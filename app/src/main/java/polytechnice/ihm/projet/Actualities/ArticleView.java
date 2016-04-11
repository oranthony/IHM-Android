package polytechnice.ihm.projet.Actualities;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    private static final String TITRE = "TITLE OK DAC";
    private TextView title;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.article_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        image.setImageResource(R.drawable.image_article);

        title = (TextView) view.findViewById(R.id.text_article);
        //text.setText("IT WORKS !");

        if (getArguments() != null) {
            Bundle args = getArguments();
            if (args.containsKey(TITRE))
                title.setText(args.getString(TITRE));
            else
                title.setText("CA MARCHE PAS");
        }
        else
            System.out.println("getArguments est NULL");

    }


    /*public static ArticleView newInstance() {
        ArticleView fragment = new ArticleView();
        return fragment;
    }*/

    public static ArticleView newInstance(String title) {
        ArticleView fragment = new ArticleView();
        Bundle args = new Bundle();
        args.putString(TITRE, title);
        fragment.setArguments(args);
        return fragment;
    }

}
