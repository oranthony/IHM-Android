package polytechnice.ihm.projet;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private static String FILE = "contact.json";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String jsondata = null;
        try {
            jsondata = jsonToStringFromAssetFolder(FILE, getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("JSONDATA" + jsondata);

        ArrayList<Person> ar =  new ParserContact(jsondata).getPeople();

        /*for(Person per : ar)
        {
            System.out.println("===============");
            System.out.println("====== name : " + per.getName());
            System.out.println("====== familyname : " + per.getFamilyname());
        }*/

        contactsAdapter = new ContactsAdapter(getContext(), R.id.lien_contact, ar);

        final View rootView = inflater.inflate(R.layout.list_contacts, container, false);

        final ListView listView = (ListView) rootView.findViewById(R.id.lien_contact);
        listView.setAdapter(contactsAdapter);


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
