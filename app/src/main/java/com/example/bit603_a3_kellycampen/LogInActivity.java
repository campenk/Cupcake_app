package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {
private final static String TAG = "Campen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Log.d(TAG, "username and password" + username + password);
                Log.d(TAG, "user is admin: " +userIsAdmin(username, password).toString());
                if (userIsAdmin(username, password)) {
                    Intent i = new Intent(getApplicationContext(), ManageUsersActivity.class);
                    startActivity(i);
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