package polytechnice.ihm.projet.Contacts;

/**
 * @author Patrice Camousseigt
 */
public class Person {

    private String name;
    private String familyname;
    private String email;
    private String room;
    private String phonenumber;

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

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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

    public String getPhonenumber() {
        return phonenumber;
    }
}
