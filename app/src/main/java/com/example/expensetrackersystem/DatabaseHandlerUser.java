package com.example.expensetrackersystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandlerUser extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "user_data";
    public static final String COL1 = "USERNAME";
    public static final String COL2 = "PASSWORD";

    public DatabaseHandlerUser(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(USERNAME TEXT PRIMARY KEY," + "PASSWORD TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String a = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(a);
        onCreate(db);
    }
}