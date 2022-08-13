package com.example.expensetrackersystem.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.expensetrackersystem.model.User;

@Dao
public interface UserDao
{
    // Custom query to get user
    @Query("SELECT * FROM user WHERE username LIKE :username")
    User findUser(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);
}
