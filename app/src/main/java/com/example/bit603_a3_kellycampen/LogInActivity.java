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

    //  create class variables // @kelly: this comment is excessive. its clear what you are doing.
    public static UserDatabase_v2 userDatabase;
    private Utilities util = new Utilities();
    private UserList userList = new UserList();
    private User currentUser;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // @kelly: personal preference but i like a space between the method declaration and the first code inside it. :shrug:
        setContentView(R.layout.activity_main);
        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase_v2.class, getString(R.string.userDatabase)).allowMainThreadQueries().build(); // @kelly: no magic strings.

        //  create variables // @kelly: again excessive commenting
        Button buttonSubmit = findViewById(R.id.buttonLogin_submit);
        EditText editTextUsername = findViewById(R.id.editTextTextLogin_username);
        EditText editTextPassword = findViewById(R.id.editTextLogin_password);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  check if user is admin // @kelly: any block like this is begging to be made a method with more or less the comment as the name
                    if (userList.userIsAdmin(editTextUsername.getText().toString(), editTextPassword.getText().toString())) {
                    Intent i = new Intent(getApplicationContext(), ManageUsersActivity.class); // @kelly: personally i'd avoid using i here as its common to use it in loops, so seeing it here is confusing. generally single letter variable names are a bad idea anyways
                    i.putExtra(getString(R.string.username), editTextUsername.getText().toString());   // @kelly: no magic strings
                    startActivity(i);
                }
                else { // @kelly: its hard to read where this else starts (where the if is) because of inconsistent indentation.
                    //  return user if they exist in database
                    users = userDatabase.dao().getUsers();
                    if (userList.userExists(editTextUsername.getText().toString(), users)) {
                        currentUser = userList.returnUser(editTextUsername.getText().toString(), users);
                    }

                    //  if user does not exist in database // @kelly: see above
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
                        else { // @kelly: we're 6 levels deep here, within multi levels of nesting. its a bad pattern. best to impose a linear flow of events. generally you can return early from a method with null etc. in invalid states which allows for this.
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