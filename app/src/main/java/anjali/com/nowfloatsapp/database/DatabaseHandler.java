package anjali.com.nowfloatsapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import anjali.com.nowfloatsapp.model.Resturent;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "favouritedatabase";
    private static final String TABLE_RESTURENT = "resturent";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CONTACTS="CREATE TABLE " + TABLE_RESTURENT + "("
                + KEY_ID +" INTEGER PRIMARY KEY,"
                + KEY_NAME +" TEXT " + ")";
        db.execSQL(CREATE_TABLE_CONTACTS);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTURENT);
        onCreate(db);
    }

    public void addFav(Resturent contact){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME, contact.getName());

        db.insert(TABLE_RESTURENT, null, values);
        System.out.println("Anjalidatabase"+values);
        db.close();
    }



    public ArrayList<Resturent> getAllContacts() {
        ArrayList<Resturent> contactList = new ArrayList<Resturent>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RESTURENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Resturent contact = new Resturent();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));

                // Adding contact to list
                contactList.add(contact);
                System.out.println("Anjalirecords"+" "+cursor.getString(3)+" "+cursor.getString(4));

            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }



}

