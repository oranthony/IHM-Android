package polytechnice.ihm.projet.Contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import polytechnice.ihm.projet.R;

/**
 * @author Patrice Camousseigt
 */
public class ContactsAdapter extends ArrayAdapter<Person> {

    public ContactsAdapter(Context context, int resource, List<Person> people) {
        super(context, resource, people);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.contact, null);
        }

        Person person = getItem(position);

        TextView nameContact = (TextView) convertView.findViewById(R.id.name_contact);
        nameContact.setText(person.getName() + " " + person.getFamilyname());

        TextView emailContact = (TextView) convertView.findViewById(R.id.email_contact);
        emailContact.setText(person.getEmail());

        return convertView;
    }


}