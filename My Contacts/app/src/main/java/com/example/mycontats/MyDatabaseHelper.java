package com.example.mycontats;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.SQLDataException;

class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private  static final String DATABASE_NAME = "Contats.db";
    private  static final int DATABASE_VERSION = 1;

    private  static final String TABLE_NAME = "mycontats";
    private  static final String COLUMN_ID = "id";
    private  static final String COLUMN_NAME = "name";
    private  static final String COLUMN_SURNAME = "surname";
    private  static final String COLUMN_NUMBER = "number";
    private  static final String COLUMN_EMAIL = "email";
    private  static final String COLUMN_TYPE = "type";
    private  static final String COLUMN_COMPANY = "company";
    private  static final String COLUMN_COUNTRY = "country";
    private  static final String COLUMN_IMAGE_ID= "imageId";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME+
                "("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUMN_NAME+" TEXT, "+
                COLUMN_SURNAME+" TEXT, "+
                COLUMN_NUMBER+" TEXT, "+
                COLUMN_EMAIL+" TEXT, "+
                COLUMN_TYPE+" TEXT, "+
                COLUMN_COMPANY+" TEXT, "+
                COLUMN_COUNTRY+" TEXT, "+
                COLUMN_IMAGE_ID+" INTEGER); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public void  addUser(String name, String last, String number, String email, String type, String company, String country, int imageId){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_SURNAME,last);
        cv.put(COLUMN_NUMBER,number);
        cv.put(COLUMN_EMAIL,email);
        cv.put(COLUMN_TYPE,type);
        cv.put(COLUMN_COMPANY,company);
        cv.put(COLUMN_COUNTRY,country);
        cv.put(COLUMN_IMAGE_ID,imageId);
        long result=db.insert(TABLE_NAME,null,cv);
        if (result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressLint("Recycle")
    Cursor readAllData(){
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if (db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE  FROM "+ TABLE_NAME);
        db.close();
    }
}
