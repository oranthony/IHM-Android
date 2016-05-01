package polytechnice.ihm.PolyNews.Contacts;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import polytechnice.ihm.PolyNews.R;

/**
 * Create a inflater of a list of people
 * @author Patrice Camousseigt
 */
public class ContactsAdapter extends ArrayAdapter<Person> {

    /**
     * Constructor
     * @param context the context
     * @param resource the resource
     * @param people the list of people
     */
    public ContactsAdapter(Context context, int resource, List<Person> people) {
        super(context, resource, people);
    }

    /**
     * Create the inflater of an article
     * @param position the position of the article
     * @param convertView the view associated
     * @param parent the parent view
     * @return the inflate of a contact
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.contact, null);
        }

        Person person = getItem(position);

        TextView nameContact = (TextView) convertView.findViewById(R.id.name_contact);
        nameContact.setText(person.getName() + " " + person.getFamilyname());

        TextView roomcontact = (TextView) convertView.findViewById(R.id.room_contact);
        roomcontact.setText("Salle : " + person.getRoom());

        TextView emailContact = (TextView) convertView.findViewById(R.id.email_contact);
        emailContact.setText(Html.fromHtml(person.getEmail()));

        TextView phonenumercontact = (TextView) convertView.findViewById(R.id.phonenumber_contact);
        phonenumercontact.setText("Tel : " + person.getPhonenumber());

        return convertView;
    }


}
