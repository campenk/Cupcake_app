package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LogInActivity extends AppCompatActivity {

    //  create class variables
    public static UserDatabase_v2 userDatabase;
    private Utilities util = new Utilities();
    private UserList userList = new UserList();
    private User currentUser;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase_v2.class, "userdb").allowMainThreadQueries().build();

        //  create variables
        Button buttonSubmit = findViewById(R.id.buttonLogin_submit);
        EditText editTextUsername = findViewById(R.id.editTextTextLogin_username);
        EditText editTextPassword = findViewById(R.id.editTextLogin_password);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  check if user is admin
                    if (userList.userIsAdmin(editTextUsername.getText().toString(), editTextPassword.getText().toString())) {
                    Intent i = new Intent(getApplicationContext(), ManageUsersActivity.class);
                    i.putExtra(getString(R.string.username), editTextUsername.getText().toString());
                    startActivity(i);
                }
                else {
                    //  return user if they exist in database
                    users = userDatabase.dao().getUsers();
                    if (userList.userExists(editTextUsername.getText().toString(), users)) {
                        currentUser = userList.returnUser(editTextUsername.getText().toString(), users);
                    }

                    //  if user does not exist in database
                    if (currentUser.equals(null)) {
                        Toast.makeText(getBaseContext(),getString(R.string.userNotFound), Toast.LENGTH_SHORT).show();
                        editTextPassword.setText("");
                        util.formatInvalidInput(editTextUsername);
                    }
                    //  else if user is found
                    else {
                        //  and if password is correct
                        if (editTextPassword.getText().toString().equals(currentUser.getPassword())) {
                            Intent i = new Intent(getApplicationContext(), ManageInventoryActivity.class);
                            i.putExtra(getString(R.string.username), editTextUsername.getText().toString());
                            startActivity(i);
                        }
                        else {
                            //  if password is incorrect
                            Toast.makeText(getBaseContext(),getString(R.string.passwordIncorrect), Toast.LENGTH_SHORT).show();
                            editTextPassword.setText("");
                            util.formatInvalidInput(editTextPassword);
                        }
                    }
                }
            }
        });
    }
}