package polytechnice.ihm.projet.Contacts;

/**
 * @author Patrice Camousseigt
 */
public class Person {

    String name;
    String familyname;
    String email;
    String room;

    public void setName(String name) {
        this.name = name;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public String getFamilyname() {
        return familyname;
    }

    public String getEmail() {
        return email;
    }

    public String getRoom() {
        return room;
    }
}
