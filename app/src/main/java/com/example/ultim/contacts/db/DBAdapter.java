package com.example.ultim.contacts.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by IVAN on 3/24/2018.
 */

public class DBAdapter  {
    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_EMAIL = "email";
    static final String KEY_PHONE = "phone";
    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "myDB";
    static final String DATABASE_TABLE = "contacts";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE = "create table contacts (_id integer primary key autoincrement, " +
            "name text not null, email text not null, phone text not null);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    /**
     * A CLASS DEDICATED TO HELPING THE ADAPTER MANAGE THE DATA AND CREATING
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * Creates a database using params given earlier
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL(DATABASE_CREATE);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        /**
         * if the database version is upgraded the data is destroyed
         * @param db
         * @param oldVersion
         * @param newVersion
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "onUpgrade: upgrading database from " + oldVersion + " to " + newVersion);
            db.execSQL("Drop TABLE IF EXISTS contacts");
            onCreate(db);
        }

    }//end of DBHelper

    //opens the db to write to it
    public DBAdapter open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //-- closes the database
    public void close(){
        DBHelper.close();
    }

    //--insert a contact to database
    public long insertContact(String name, String email, String phone){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_EMAIL, email);
        initialValues.put(KEY_PHONE, phone);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //--deletes a particular contact--
    public boolean deleteContact(long rowID){
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowID, null) > 0;
    }

    //--retrieves all contacts--
    public Cursor getAllCOntacts(){
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_PHONE}, null, null, null, null, null);
    }

    //--retrieves a particular contact
    public Cursor getContact(long rowID){
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_PHONE}, KEY_ROWID + "="
                + rowID, null, null, null, null, null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //--updates a contact--
    public boolean update(long rowID, String name, String email, String phone){
        ContentValues args = new ContentValues();
        args.put(KEY_PHONE, phone);
        args.put(KEY_EMAIL, email);
        args.put(KEY_NAME, name);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowID, null ) > 0;
    }


}

