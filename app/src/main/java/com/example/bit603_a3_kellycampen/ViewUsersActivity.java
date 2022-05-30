package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
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

        List<User> users = userDatabase.dao().getUsers();
        String output = "Item\t\t\tQuantity\t\t\tType\n";

        for (User user : users) {
            output += user.getUsername() + "\t\t\t" + user.getPassword() + "\t\t\t" + user.getEmployeeNumber().toString() + "\n";
        }

        textViewOutput.setText(output);
    }
}