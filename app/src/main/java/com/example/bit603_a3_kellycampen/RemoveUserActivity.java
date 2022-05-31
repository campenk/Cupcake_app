package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class RemoveUserActivity extends AppCompatActivity {
    public static UserDatabase_v2 userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_user);
        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase_v2.class, "userdb").allowMainThreadQueries().build();


        EditText editTextUsername = findViewById(R.id.editTextRemoveUser_username);
        Button buttonFindUser = findViewById(R.id.buttonRemoveUser_find);
        Button buttonMenu = findViewById(R.id.buttonRemoveUser_menu);

        buttonFindUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User userToRemove;
                List<User> users = userDatabase.dao().getUsers();
                for (User user : users) {
                    if (user.getUsername().equals(editTextUsername.getText().toString())) {
                        // Instantiate
                        AlertDialog.Builder builder = new AlertDialog.Builder(RemoveUserActivity.this);
                        // Set characteristics
                        builder.setMessage("Do you want to remove the user\nUsername: " +user.getUsername() + "\nDate of Birth: " + user.getDateOfBirth() + "\nEmployee number: " + user.getEmployeeNumber())
                                .setTitle("Confirm removal of user");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                userDatabase.dao().deleteByUsername(user.getUsername());
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Cancel button was clicked
                            }
                        });
                        // Get AlertDialog
                        AlertDialog dialog = builder.create();

                        // Show dialog
                        dialog.show();                    }
                }






            }
        });



        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ManageUsersActivity.class);
                startActivity(i);
            }
        });
    }
}