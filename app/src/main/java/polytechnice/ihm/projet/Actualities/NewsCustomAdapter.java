package polytechnice.ihm.projet.Actualities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

import polytechnice.ihm.projet.FirstFragment;
import polytechnice.ihm.projet.R;


/**
 * @author Patrice Camousseigt
 */
public class NewsCustomAdapter extends ArrayAdapter<Article> {

    private String mediaPath;

    public NewsCustomAdapter(Context context, int resource) {
        super(context, resource);
    }

    public NewsCustomAdapter(Context context, int resource, List<Article> article) {
        super(context, resource, article);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.fragment_news_card, null);
        }

        Article article = getItem(position);

        TextView titleView = (TextView) convertView.findViewById(R.id.title);
        TextView dateView = (TextView) convertView.findViewById(R.id.date);
        TextView categorieView = (TextView) convertView.findViewById(R.id.categorie);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.previewImage);

        titleView.setText(article.getTitle());
        dateView.setText(article.getDate());
        categorieView.setText(article.getCategory().name());
        mediaPath = article.getUrl();

        if(article.getUrl().contains("youtube")) {
            mediaPath = toYoutubePreview(mediaPath);
        }

        Picasso.with(getContext())
                .load(mediaPath)
                .resize(500, 300)
                .centerCrop()
                .into(imageView);


        return convertView;

    }

    private String toYoutubePreview(String link) {
        return "http://i1.ytimg.com/vi/" + link.split("=")[1] + "/default.jpg";
    }


    public String getMediaPath() {
        return mediaPath;
    }
}
