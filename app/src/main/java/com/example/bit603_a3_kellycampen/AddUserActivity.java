package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AddUserActivity extends AppCompatActivity {
    public static UserDatabase_v2 userDatabase;
    private final static String TAG = "Campen";
    DatePickerDialog picker;
    final Calendar cldr = Calendar.getInstance();
    int day = cldr.get(Calendar.DAY_OF_MONTH);
    int month = cldr.get(Calendar.MONTH);
    String dateOfBirth;
    Utilities util = new Utilities();
    UserList userList = new UserList();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase_v2.class, "userdb").allowMainThreadQueries().build();
        Intent i = getIntent();
        String username = i.getStringExtra(getString(R.string.username));


        final Button buttonSubmit = findViewById(R.id.buttonAddUser_submit);
        final EditText editTextUsername = findViewById(R.id.editTextAddUser_username);
        final EditText editTextPassword = findViewById(R.id.editTextAddUser_Password);
        final EditText editTextDateOfBirth = findViewById(R.id.editTextAddUser_dateOfBirth);
        final EditText editTextEmployeeNumber = findViewById(R.id.editTextAddUser_employeeNumber);
        final EditText editTextPhoneNumber = findViewById(R.id.editTextAddUser_phoneNumber);
        final EditText editTextAddress = findViewById(R.id.editTextAddUser_address);
        final Button buttonReset = findViewById(R.id.buttonAddUser_reset);
        final Button buttonMenu = findViewById(R.id.buttonAddUser_menu);
        final Button buttonLogout = findViewById(R.id.buttonAddUser_logout);
        List<User> users = userDatabase.dao().getUsers();

        // date picker dialog
        picker = new DatePickerDialog(AddUserActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        editTextDateOfBirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        setDay(dayOfMonth);
                        setMonth(monthOfYear);
                        setYear(year);
                        cldr.set(year, monthOfYear,  dayOfMonth);
                        Date date = new Date(cldr.getTimeInMillis());
                        setDateOfBirth(date.toString());
                        editTextDateOfBirth.setText(dateOfBirth);
                    }
                },year, month, day);
        //  TODO: Change to when receives focus
        editTextDateOfBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    util.hideKeyboard(editTextDateOfBirth);
                    picker.show();
                } else {
                    picker.hide();
                }
            }
        });

        editTextDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.hideKeyboard(editTextDateOfBirth);
                picker.show();
            }
        });


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<EditText, String> requiredFields = new HashMap<>();
                Log.d(TAG, "create hashmap");
                if (userList.userExists(editTextUsername.getText().toString(), users)) {
                    Log.d(TAG, "User already exists");
                    Toast.makeText(getBaseContext(), "A user already exists with this username!", Toast.LENGTH_SHORT).show();
                    util.formatInvalidInput(editTextUsername);
                }

                else {
                        Log.d(TAG, "user is not null");
                    User user = new User();

                    if (util.checkValidString(editTextUsername, requiredFields)) {
                        user.setUsername(editTextUsername.getText().toString());
                    }
                    if (util.checkValidString(editTextPassword, requiredFields)) {
                        user.setPassword(editTextPassword.getText().toString());

                    }
                    if (util.checkValidString(editTextAddress, requiredFields)) {
                        user.setAddress(editTextAddress.getText().toString());
                    }

                    if (util.checkValidString(editTextPhoneNumber, requiredFields)) {
                        user.setPhoneNumber(editTextPhoneNumber.getText().toString());
                    }

                    if (util.checkValidInteger(editTextEmployeeNumber, requiredFields)) {
                        user.setEmployeeNumber(Integer.parseInt(editTextEmployeeNumber.getText().toString()));
                    }

                    Log.d(TAG, editTextDateOfBirth.getText().toString());
                    Log.d(TAG, "checkValidDate function returns " + util.checkValidDate(editTextDateOfBirth, requiredFields).toString());
                    if (util.checkValidDate(editTextDateOfBirth, requiredFields)) {
                        Log.d(TAG, "if checkValidDate function returns true - why tho");
                        user.setDateOfBirth(editTextDateOfBirth.getText().toString());
                        Log.d(TAG, editTextDateOfBirth.getText().toString());
                    }

                    if (!requiredFields.containsValue(null)) {

                            Log.d(TAG, "no values are null");
                            userDatabase.dao().addUser(user);
                            Toast.makeText(getBaseContext(),"User added successfully!", Toast.LENGTH_SHORT).show();

                        }
                    else {
                        Toast.makeText(getBaseContext(),"Invalid inputs, please try again", Toast.LENGTH_SHORT).show();

                        Log.d(TAG, "required fields contains null values");

                    }
                    }
                }

        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.formatReset(editTextUsername);
                util.formatReset(editTextPassword);
                util.formatReset(editTextDateOfBirth);
                util.formatReset(editTextEmployeeNumber);
                util.formatReset(editTextPhoneNumber);
                util.formatReset(editTextAddress);
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

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    int year = cldr.get(Calendar.YEAR);

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

