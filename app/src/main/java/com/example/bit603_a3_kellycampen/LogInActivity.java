package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LogInActivity extends AppCompatActivity {
    private final static String TAG = "Campen";
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

        Button buttonSubmit = findViewById(R.id.buttonLogin_submit);
        EditText editTextUsername = findViewById(R.id.editTextTextLogin_username);
        EditText editTextPassword = findViewById(R.id.editTextLogin_password);
        editTextUsername.setText("admin");
        editTextPassword.setText(R.string.adminpassword);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  check if user is admin
                    if (userList.userIsAdmin(editTextUsername.getText().toString(), editTextPassword.getText().toString())) {
                        Log.d(TAG, "User is admin = true");
                    Intent i = new Intent(getApplicationContext(), ManageUsersActivity.class);
                        i.putExtra(getString(R.string.username), editTextUsername.getText().toString());
                        startActivity(i);
                        Log.d(TAG, "Start Activity");
                }
                else {
                        users = userDatabase.dao().getUsers();
                        currentUser = userList.userExists(editTextUsername.getText().toString(), users);


                    if (currentUser.equals(null)) {
                        Toast.makeText(getBaseContext(),"User not found!", Toast.LENGTH_SHORT).show();
                        editTextPassword.setText("");
                        util.formatInvalidInput(editTextUsername);
                    }
                    else {
                        if (editTextPassword.getText().toString().equals(currentUser.getPassword())) {
                            Intent i = new Intent(getApplicationContext(), ManageInventoryActivity.class);
                            i.putExtra(getString(R.string.username), editTextUsername.getText().toString());
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(getBaseContext(),"Password incorrect!", Toast.LENGTH_SHORT).show();
                            editTextPassword.setText("");
                            util.formatInvalidInput(editTextPassword);

                        }
                    }
                    }
                }





        });









    }


}