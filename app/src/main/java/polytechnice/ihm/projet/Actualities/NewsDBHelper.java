package polytechnice.ihm.projet.Actualities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import polytechnice.ihm.projet.BuildConfig;

/**
 * Loads the polynews_database database
 */
public class NewsDBHelper extends SQLiteOpenHelper {

    private final static String DB_PATH = "/data/data/" + BuildConfig.APPLICATION_ID + "/";
    private final static String DB_NAME = "polynews_database";

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public NewsDBHelper(Context context) throws SQLException, IOException {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        boolean dbexist = checkDataBase();
        if (dbexist) {
            System.out.println("La database existe");
            openDataBase();
        } else {
            System.out.println("La database n'existe pas");
            createDataBase();
        }
        readDB();
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(!dbExist){
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    public List<Article> readDB() {
        List<Article> list = new ArrayList<>();

        try {
            openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = myDataBase.rawQuery("SELECT * FROM news ORDER BY date DESC", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Article article = new Article();

            article.setTitle(cursor.getString(0));
            article.setContent(cursor.getString(1));
            article.setAuthor(cursor.getString(2));
            article.setDate(cursor.getString(3));
            article.setCategory(Article.Categories.getCategory(cursor.getInt(4)));
            article.setType(Article.Types.getType(cursor.getInt(5)));
            article.setUrl(cursor.getString(6));

            list.add(article);

            cursor.moveToNext();
        }
        cursor.close();
        close();
        return list;
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch(SQLiteException e){
            System.out.println("DATABASE INTROUVABLE");
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        System.out.println(myPath);
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}