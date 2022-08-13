package com.example.expensetrackersystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
//Created by Corvel Allen
public class RegistrationDatabaseHandler extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "registration.db";
    public static final String TABLE_NAME = "registration_data";
    public static final String COL1 = "Email";
    public static final String COL2 = "PASSWORD";
    public static final String COL3= "PHONE";
    public static final String COL4 = "USERTYPE";

    public RegistrationDatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(COL1 PRIMARY KEY," + "COL2,"+"COL3,"+"COL4)";
        db.execSQL(createTable);
    }
    public void insertData(String email, int phone, String password, String userType)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL1, email);
        values.put(COL2, password);
        values.put(COL3, phone);
        values.put(COL4, userType);
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String a = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(a);
        onCreate(db);
    }
}

