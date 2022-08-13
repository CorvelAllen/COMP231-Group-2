package com.example.expensetrackersystem;

// Created by Ubaid Delawala - Group 2

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expensetrackersystem.Dao.UserDao;
import com.example.expensetrackersystem.model.User;

import java.sql.SQLClientInfoException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LoginActivity extends AppCompatActivity
{
    // Define variable based on login layout
    private EditText eUsername, ePassword;
    private Button eLogin, eRegister;

    //Validate User
    boolean isValid;

    //Username and password to match
    private String username;
    private String password;

    AppDatabase appDb;

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Create db instance
        appDb = AppDatabase.getInstance(LoginActivity.this);

        // Bind Variables with layout
        eUsername = findViewById(R.id.etUsername);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        eRegister = findViewById(R.id.btnRegister);

        insertUserInDb("admin", "admin");

        // Add on CLick Listener to Login Button
        eLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Get User Input
                String inputUsername = eUsername.getText().toString().toLowerCase();
                String inputPassword = ePassword.getText().toString();

                User user = getUserFormDb(inputUsername);
                if(user==null)
                {
                    Toast.makeText(LoginActivity.this, "Incorrect Credentials!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Null check
                if(inputUsername.isEmpty() || inputPassword.isEmpty())
                {
                    // Inform user to fill in both fields
                    Toast.makeText(LoginActivity.this, "Please Enter all The Details",
                            Toast.LENGTH_SHORT).show();
                }else
                {
                    isValid = validateUser(inputUsername, inputPassword, user);
                }

                // Validate User
                if(!isValid)
                {
                    Toast.makeText(LoginActivity.this, "Incorrect Credentials!",
                            Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(LoginActivity.this, "Login Successful!",
                            Toast.LENGTH_SHORT).show();

                    // Go to Next Activity
                    Intent intent = new Intent(LoginActivity.this,
                            MainActivity.class);

                    startActivity(intent);
                }
            }
        });

        // Add onClickListener on Register Button
//        eRegister.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    private boolean validateUser(String inputUsername, String inputPassword, User user)
    {
        // Validate username and Password
        if(inputUsername.toLowerCase().equals(user.getUsername()) &&
                inputPassword.toLowerCase().equals(user.getPassword()))
        {
            return true;
        }
        return false;
    }

    public User getUserFormDb(String inputUsername)
    {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                insertUserInDb("admin", "password");
                user = appDb.userDao().findUser(inputUsername);
            }
        });
        return user;
    }

    public void insertUserInDb(String username, String password)
    {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                User user = new User(username, password);
                appDb.userDao().insertUser(user);
            }
        });

    }

}