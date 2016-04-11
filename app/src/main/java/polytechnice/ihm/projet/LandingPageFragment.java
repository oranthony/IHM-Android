package polytechnice.ihm.projet;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by antho on 04/04/2016.
 */
public class LandingPageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.landing_page, container, false);
        View view = inflater.inflate(R.layout.landing_page, container, false);
        return view;

    }
}
