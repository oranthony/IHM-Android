package polytechnice.ihm.projet.Contacts;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import polytechnice.ihm.projet.BuildConfig;

/**
 * Parse a json file to get data from contacts
 * @author Patrice Camousseigt
 */
public class ParserContact {

    private ArrayList<Person> people = new ArrayList<>();
    private JsonObject jsonObject;


    /**
     * Constructor
     * @param jsonString A string with information inside to parse
     */
    public ParserContact(String jsonString) {

        JsonParser parser = new JsonParser();
        this.jsonObject = parser.parse(jsonString).getAsJsonObject();

        try {

            JsonArray companyList = (JsonArray) this.jsonObject.get("people");

            for(int i = 0; i < companyList.size(); i++)
            {
                JsonObject jsonObject = companyList.get(i).getAsJsonObject();
                JsonElement nameElement = jsonObject.get("name");
                JsonElement familynameElement = jsonObject.get("familyname");
                JsonElement emailElement = jsonObject.get("email");
                JsonElement roomElement = jsonObject.get("room");
                JsonElement phonenumberElement = jsonObject.get("phonenumber");

                String name = "";
                String familyname = "";
                String email = "";
                String room = "";
                String phonenumber = "";

                if(nameElement != null)
                    name = nameElement.getAsString();
                if(familynameElement != null)
                    familyname = familynameElement.getAsString();
                if(emailElement != null)
                    email = emailElement.getAsString();
                if(roomElement != null)
                    room = roomElement.getAsString();
                if(phonenumberElement != null)
                    phonenumber = phonenumberElement.getAsString();

                Person p = new Person();
                p.setName(name);
                p.setFamilyname(familyname);
                p.setEmail(email);
                p.setRoom(room);
                p.setPhonenumber(phonenumber);

                people.add(i, p);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Person> getPeople() {
        return people;
    }
}
