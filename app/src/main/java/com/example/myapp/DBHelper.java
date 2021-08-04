package com.example.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Log.db";

    public DBHelper(Context context) {
        super(context,"Log.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(firstName TEXT,eMail TEXT primary key,mobileNumber TEXT,dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int il) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String firstName, String eMail, String mobileNumber, String dob) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", String.valueOf(firstName));
        contentValues.put("eMail",eMail);
        contentValues.put("mobileNumber",mobileNumber);
        contentValues.put("dob",dob);
        long result = MyDB.insert("users",null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Boolean checkUsername(String eMail) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where eMail = ?", new String[] {eMail});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
