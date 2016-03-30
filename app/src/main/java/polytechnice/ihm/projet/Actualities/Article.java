package polytechnice.ihm.projet.Actualities;

/**
 * Created by patricecamousseigt on 16/03/2016.
 */
public class Article {
    private int id;
    private String title;
    private String content;
    private String author;
    private String date;
    private Types type;
    private Categories category;
    private String url;

    public enum Categories {
        POLITIQUE(1),
        SOCIETE(2);

        private int id;

        Categories(int id){
            this.id = id;
        }

        public static Categories getCategory(int id){
            if(id == 1) return POLITIQUE;

            return SOCIETE;
        }
    }

    public enum Types {
        IMAGE(0),
        VIDEO(1);

        private int id;

        Types(int id){
            this.id = id;
        }

        public static Types getType(int id){
            if(id == 0) return IMAGE;

            return VIDEO;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


