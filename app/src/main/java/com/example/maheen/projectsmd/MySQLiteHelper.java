package com.example.maheen.projectsmd;

import java.util.LinkedList;
import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "HajjGuideFinalll";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_UMRAH_TABLE = "CREATE TABLE umrah ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "stepname TEXT, "+
                "description TEXT,"+
                "imagename INTEGER  )";

        String CREATE_HAJJ_TABLE = "CREATE TABLE hajj ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "stepname TEXT, "+
                "description TEXT,"+
                "imagename INTEGER  )";

        // create books table
        db.execSQL(CREATE_UMRAH_TABLE);
        db.execSQL(CREATE_HAJJ_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS umrah ");
        db.execSQL("DROP TABLE IF EXISTS hajj ");

        // create fresh books table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    // Books table name
    private static final String TABLE_UMRAH = "umrah";
    private static final String TABLE_HAJJ = "Hajj";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_STEPNAME= "stepname";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGENAME = "imagename";

    private static final String[] COLUMNS = {KEY_ID,KEY_STEPNAME,KEY_DESCRIPTION,KEY_IMAGENAME};

    public void addUmrahitem(Umrahclass umrah,String tablename){
        Log.d("umrahitem",umrah.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_STEPNAME, umrah.getMethodname()); // get title
        values.put(KEY_DESCRIPTION, umrah.getDescription()); // get author
        values.put(KEY_IMAGENAME, umrah.getImagename());


        // 3. insert
        db.insert(tablename, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Umrahclass getUmrah(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_UMRAH, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Umrahclass book = new Umrahclass();
        book.setId(Integer.parseInt(cursor.getString(0)));
        book.setMethodname(cursor.getString(1));
        book.setDescription(cursor.getString(2));
        book.setImagename(cursor.getInt(3));

        Log.d("getumrah("+id+")", book.toString());

        // 5. return book
        return book;
    }

    // Get All Books
    public List<Umrahclass> getAllUmrah(String tablename) {
        List<Umrahclass> umrahs = new LinkedList<Umrahclass>();

        // 1. build the query
        String query = "SELECT  * FROM " + tablename;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Umrahclass umrah1 = null;
        if (cursor.moveToFirst()) {
            do {
                umrah1 = new Umrahclass();
                umrah1.setId(Integer.parseInt(cursor.getString(0)));
                umrah1.setMethodname(cursor.getString(1));
                umrah1.setDescription(cursor.getString(2));
                umrah1.setImagename(cursor.getInt(3));

                // Add book to books
                umrahs.add(umrah1);
            } while (cursor.moveToNext());
        }

        Log.d("getAllUmrah()", umrahs.toString());

        // return books
        return umrahs;
    }


}

