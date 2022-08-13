package com.example.expensetrackersystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.expensetrackersystem.Dao.UserDao;
import com.example.expensetrackersystem.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    private static final String DB_NAME = "expense_database";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                    DB_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract UserDao userDao();
}
