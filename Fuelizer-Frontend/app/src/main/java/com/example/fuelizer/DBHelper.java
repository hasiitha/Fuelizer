package com.example.fuelizer;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

    public String USERTYPE;

    // initializing the DB name is SQLite
    public static final String DBNAME = "UserLogin.db";
    private static final String TAG = "tag";

    // constructor
    public DBHelper(Context context) {
        super(context, "UserLogin.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username TEXT primary key, password TEXT, userType TEXT)"); // creating the users table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop TABLE if exists users");
    }

    // inserting username and password to the sqlite DB
    public Boolean insertData(String username, String password, String userType){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("userType", userType);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    // check if user already exists
    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //checking the user inputted UN and PW with DB values
    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public void checkUserType(String uname){
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{uname});


        while(cursor.moveToNext()){

            Log.e(TAG,"userType - "+cursor.getString(2));
            USERTYPE = cursor.getString(2);
        }

        cursor.close();

    }




}
