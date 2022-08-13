package com.example.expensetrackersystem;
//created by Corvel Allen
// Student #: 301078286
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

public class Registration extends AppCompatActivity {

    // Define variable based on login layout
    private EditText email,phone,password,cPassword,userType;
    private Button registerBtn;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Create db instance
        RegistrationDatabaseHandler AppDb = new RegistrationDatabaseHandler(Registration.this);

        // Bind Variables with layout
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cPassword = findViewById(R.id.cPassword);
        phone = findViewById(R.id.phone);
        userType = findViewById(R.id.userType);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Get User Input
                String emailInput = email.getText().toString().toLowerCase();
                int phoneInput = Integer.parseInt(String.valueOf(phone.getText()));
                String passwordInput = password.getText().toString();
                String cPasswordInput = cPassword.getText().toString();
                String userTypeInput = userType.getText().toString();

                if(passwordInput!=cPasswordInput){
                    Toast.makeText(Registration.this, "Passwords need to match!",
                            Toast.LENGTH_SHORT).show();
                }
                User newUser = new User(emailInput, phoneInput, passwordInput,userTypeInput);

                if(newUser!=null) {
                    Toast.makeText(Registration.this, "Account exists!",
                            Toast.LENGTH_SHORT).show();
                }
                // Null check
                if(emailInput.isEmpty()||passwordInput.isEmpty()||userTypeInput.isEmpty())
                {
                    // Inform user to fill in both fields
                    Toast.makeText(Registration.this, "Please Enter all The Details",
                            Toast.LENGTH_SHORT).show();
                }
                AppDb.insertData(emailInput,phoneInput,passwordInput,userTypeInput);
                    Toast.makeText(Registration.this,"Account Created",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Registration.this,LoginActivity.class);
                    startActivity(intent);
            }
        });

    }
}
