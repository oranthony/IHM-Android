package polytechnice.ihm.projet;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import polytechnice.ihm.projet.Contacts.ContactsAdapter;
import polytechnice.ihm.projet.Contacts.ParserContact;
import polytechnice.ihm.projet.Contacts.Person;

/**
 * Creates the view of the fourth fragment Contacts
 * @author Patrice Camousseigt
 */
public class ContactsFragment extends Fragment {

    private final static String FILE = "contact.json";

    /**
     * Creates the view with an adapter from list of people parsed
     * @param inflater the inflater
     * @param container the container
     * @param savedInstanceState the saved instances
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String jsondata = null;
        try {
            jsondata = jsonToStringFromAssetFolder(FILE, getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        final ArrayList<Person> people =  new ParserContact(jsondata).getPeople();

        ContactsAdapter contactsAdapter = new ContactsAdapter(getContext(), R.id.lien_contact, people);

        final View rootView = inflater.inflate(R.layout.list_contacts, container, false);

        final ListView listView = (ListView) rootView.findViewById(R.id.lien_contact);
        listView.setAdapter(contactsAdapter);

        return rootView;
    }

    /**
     * Creates a new instance of contacts Fragment
     * @return the fragment created
     */
    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
        return fragment;
    }

    /**
     * Convert a json from a file to a string
     * @param fileName the file
     * @param context the context
     * @return the string
     * @throws IOException
     */
    public static String jsonToStringFromAssetFolder(String fileName, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(fileName);

        byte[] data = new byte[file.available()];
        file.read(data);
        file.close();
        return new String(data);
    }


}
