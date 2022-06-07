package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RemoveUserActivity extends AppCompatActivity {
    //  create class variables
    public static UserDatabase_v2 userDatabase;
    private User userToRemove = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_user);
        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase_v2.class, "userdb").allowMainThreadQueries().build();
        Intent i = getIntent();
        String username = i.getStringExtra(getString(R.string.username));

        //  create variables
        final EditText editTextUsername = findViewById(R.id.editTextRemoveUser_username);
        final Button buttonFindUser = findViewById(R.id.buttonRemoveUser_find);
        final Button buttonMenu = findViewById(R.id.buttonRemoveUser_menu);
        final Button buttonLogout = findViewById(R.id.buttonRemoveUser_logout);
        final Button buttonRemoveAll = findViewById(R.id.button);

        buttonFindUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                List<User> users = userDatabase.dao().getUsers();
                for (User user : users) {
                    if (user.getUsername().equals(editTextUsername.getText().toString())) {
                        userToRemove = user;                    }
                }
                if (userToRemove != null) {

                        // Instantiate
                        AlertDialog.Builder builder = new AlertDialog.Builder(RemoveUserActivity.this);
                        // Set characteristics
                        builder.setMessage("Do you want to remove the user\nUsername: " +userToRemove.getUsername() + "\nDate of Birth: " + userToRemove.getDateOfBirth() + "\nEmployee number: " + userToRemove.getEmployeeNumber())
                                .setTitle("Confirm removal of user");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                userDatabase.dao().deleteByUsername(userToRemove.getUsername());
                                Toast.makeText(getBaseContext(),"User removed!", Toast.LENGTH_SHORT).show();

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
                else {
                    Toast.makeText(getBaseContext(),"User not found!", Toast.LENGTH_SHORT).show();

                }
                }








        });



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

        buttonRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDatabase.dao().clearAllUsers();

            }
        });
    }
}