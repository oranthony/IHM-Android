package polytechnice.ihm.PolyNews.Actualities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

import polytechnice.ihm.PolyNews.R;


/**
 * @author Anthony Loroscio
 * @author Patrice Camousseigt
 */
public class NewsCustomAdapter extends ArrayAdapter<Article> {

    private String mediaPath;

    /**
     * Constructor
     * @param context the context
     * @param resource the resource
     * @param article the list of articles
     */
    public NewsCustomAdapter(Context context, int resource, List<Article> article) {
        super(context, resource, article);
    }

    /**
     * Creates an adapter with cardviews with data set
     * @param position
     * @param convertView
     * @param parent
     * @return the view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.fragment_news_card, null);
        }

        Article article = getItem(position);

        TextView titleView = (TextView) convertView.findViewById(R.id.title);
        TextView dateView = (TextView) convertView.findViewById(R.id.date);
        TextView categoryView = (TextView) convertView.findViewById(R.id.categorie);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.previewImage);

        titleView.setText(article.getTitle());
        dateView.setText(withoutHours(article.getDate()));
        categoryView.setText(article.getCategory().name());
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

    /**
     * Concatenation of the link
     * @param link the link wanted to be concatenated
     * @return the link concatenated
     */
    private String toYoutubePreview(String link) {
        return "http://i1.ytimg.com/vi/" + link.split("=")[1] + "/default.jpg";
    }

    private String withoutHours(String date) {
        return date.split(" ")[0];
    }

}
