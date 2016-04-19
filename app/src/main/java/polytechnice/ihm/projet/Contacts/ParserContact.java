package polytechnice.ihm.projet.Contacts;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import polytechnice.ihm.projet.BuildConfig;

/**
 * @author Patrice Camousseigt
 */
public class ParserContact {

    ArrayList<Person> people = new ArrayList<>();
    JsonObject jsonObject;


    public ParserContact(String jsonString) {

        JsonParser parser = new JsonParser();
        this.jsonObject = parser.parse(jsonString).getAsJsonObject();

        try {

            JsonArray companyList = (JsonArray) this.jsonObject.get("people");

            for(int i = 0; i < companyList.size(); i++)
            {
                JsonObject jsonObject = companyList.get(i).getAsJsonObject();
                String name = jsonObject.get("name").getAsString();
                String familyname = jsonObject.get("familyname").getAsString();

                Person p = new Person();
                p.setName(name);
                p.setFamilyname(familyname);

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
