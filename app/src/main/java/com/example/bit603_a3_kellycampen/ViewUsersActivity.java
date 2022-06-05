package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ViewUsersActivity extends AppCompatActivity {
    public static UserDatabase_v2 userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase_v2.class, "userdb").allowMainThreadQueries().build();

        final TextView textViewOutput = findViewById(R.id.textViewViewUsers_output);
        final Button buttonMenu = findViewById(R.id.buttonViewUsers_menu);

        List<User> users = userDatabase.dao().getUsers();
        String output = "";

        for (User user : users) {
            output += "------------------------------\n"
                    + "\nUsername:\t" + user.getUsername()
                    + "\nPassword:\t" + user.getPassword()
                    + "\nEmployee Number:\t" + user.getEmployeeNumber().toString()
                    + "\nDate of Birth:\t" + user.getDateOfBirth()
                    + "\nPhone Number\t: " + user.getPhoneNumber()
                    + "\nAddress:\t" + user.getAddress() + "\n";
        }

        textViewOutput.setText(output);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ManageUsersActivity.class);
                startActivity(i);
            }
        });
    }

}