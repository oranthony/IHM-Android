package polytechnice.ihm.projet;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import polytechnice.ihm.projet.Contacts.ContactsAdapter;
import polytechnice.ihm.projet.Contacts.ParserContact;
import polytechnice.ihm.projet.Contacts.Person;

/**
 * @author Patrice Camousseigt
 */
public class ThirdFragment extends Fragment {

    ContactsAdapter contactsAdapter;
    private final static String FILE = "contact.json";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String jsondata = null;
        try {
            jsondata = jsonToStringFromAssetFolder(FILE, getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        final ArrayList<Person> people =  new ParserContact(jsondata).getPeople();

        contactsAdapter = new ContactsAdapter(getContext(), R.id.lien_contact, people);

        final View rootView = inflater.inflate(R.layout.list_contacts, container, false);

        final ListView listView = (ListView) rootView.findViewById(R.id.lien_contact);
        listView.setAdapter(contactsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, people.get(position).getEmail());
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, ""));

            }
        });



        return rootView;
    }

    public static ThirdFragment newInstance() {
        ThirdFragment fragment = new ThirdFragment();
        return fragment;
    }

    public static String jsonToStringFromAssetFolder(String fileName,Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(fileName);

        byte[] data = new byte[file.available()];
        file.read(data);
        file.close();
        return new String(data);
    }


}
