package com.example.expensetrackersystem.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User
{
    @PrimaryKey @NonNull
    String username, email;
    String password, userType;
    int phone;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String email,int phone, String password,String userType) {
        this.email = email;
        this.phone = phone;
        this.password= password;
        this.userType=userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {this.password = password;}
    public void setPhone(int phone) {this.phone = phone;}
    public void setUserType(String userType) {this.userType = userType;}
}
