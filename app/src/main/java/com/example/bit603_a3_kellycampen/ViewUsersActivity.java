package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ViewUsersActivity extends AppCompatActivity {
    public static UserDatabase_v2 userDatabase;
    final private String TAG = "Campen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);
        Log.d(TAG, "onCreate");
        Intent i = getIntent();
        String username = i.getStringExtra(getString(R.string.username));
        Log.d(TAG, "get Intent");

        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase_v2.class, "userdb").allowMainThreadQueries().build();
        Log.d(TAG, "database creation");

        final TextView textViewOutput = findViewById(R.id.textViewViewUsers_output);
        final Button buttonMenu = findViewById(R.id.buttonViewUsers_menu);
        final Button buttonLogout = findViewById(R.id.buttonViewUsers_logout);
        Log.d(TAG, "create variables");

        List<User> users = userDatabase.dao().getUsers();
        Log.d(TAG, "make list of users");

        String output = "";
        Log.d(TAG, "create output string");


        for (User user : users) {
            Log.d(TAG, user.toString());

            output += "------------------------------\n"
                    + "\nUsername:\t" + user.getUsername()
                    + "\nPassword:\t" + user.getPassword()
                    + "\nEmployee Number:\t" + user.getEmployeeNumber().toString()
                    + "\nDate of Birth:\t" + user.getDateOfBirth()
                    + "\nPhone Number\t: " + user.getPhoneNumber()
                    + "\nAddress:\t" + user.getAddress() + "\n";
            Log.d(TAG, user.getUsername() + " added to output");

        }


        textViewOutput.setText(output);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ManageUsersActivity.class);
                i.putExtra(getString(R.string.username), username);
                startActivity(i);
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(i);
            }
        });
    }

}