package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase_v2.class, "userdb").allowMainThreadQueries().build();
        Utilities util = new Utilities();

        Button buttonSubmit = findViewById(R.id.buttonLogin_submit);
        EditText editTextUsername = findViewById(R.id.editTextTextLogin_username);
        EditText editTextPassword = findViewById(R.id.editTextLogin_password);
        editTextUsername.setText(R.string.adminusername);
        editTextPassword.setText(R.string.adminpassword);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
              //  Log.d(TAG, "username and password" + username + password);
              //  Log.d(TAG, "user is admin: " +userIsAdmin(username, password).toString());
                if (userIsAdmin(editTextUsername.getText().toString(), editTextPassword.getText().toString())) {
                    Intent i = new Intent(getApplicationContext(), ManageUsersActivity.class);
                    startActivity(i);
                }
                else {
                    User currentUser = null;
                    List<User> users = userDatabase.dao().getUsers();
                    for (User user : users) {
                        if (editTextUsername.getText().toString().equals(user.getUsername())) {
                            currentUser = user;
                        }
                    }

                    if (currentUser.equals(null)) {
                        Toast.makeText(getBaseContext(),"User not found!", Toast.LENGTH_SHORT).show();
                        editTextPassword.setText("");
                        util.formatInvalidInput(editTextUsername);
                    }
                    else {
                        if (editTextPassword.getText().toString().equals(currentUser.getPassword())) {
                            Intent i = new Intent(getApplicationContext(), ManageInventoryActivity.class);
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
    //  check if user is admin
    public Boolean userIsAdmin (String username, String password) {

        //  TODO: Use string resource
        if (username.equals("admin") && password.equals("CookieManagement84")) {
            return true;
        }
        return false;
    }

}