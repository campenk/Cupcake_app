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

        Button buttonSubmit = findViewById(R.id.buttonAddUser_submit);
        EditText editTextUsername = findViewById(R.id.editTextAddUser_username);
        EditText editTextPassword = findViewById(R.id.editTextAddUser_Password);
        EditText editTextDateOfBirth = findViewById(R.id.editTextAddUser_dateOfBirth);
        EditText editTextEmployeeNumber = findViewById(R.id.editTextAddUser_employeeNumber);
        EditText editTextPhoneNumber = findViewById(R.id.editTextAddUser_phoneNumber);
        EditText editTextAddress = findViewById(R.id.editTextAddUser_address);
        Button buttonReset = findViewById(R.id.buttonAddUser_reset);
        Button buttonMenu = findViewById(R.id.buttonAddUser_menu);
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
                User user = new User();

                if (userList.userExists(editTextUsername.getText().toString(), users) == null) {
                    util.checkValidString(editTextUsername, requiredFields);
                    util.checkValidString(editTextPassword, requiredFields);
                    util.checkValidString(editTextAddress, requiredFields);
                    util.checkValidString(editTextPhoneNumber, requiredFields);
                    util.checkValidInteger(editTextEmployeeNumber, requiredFields);
                    //  TODO: Improve method for validating dob input
                    util.checkValidString(editTextDateOfBirth, requiredFields);

                    if (!requiredFields.containsValue(null)) {
                        user.setUsername(editTextUsername.getText().toString());
                        user.setPassword(editTextPassword.getText().toString());
                        user.setAddress(editTextAddress.getText().toString());
                        user.setPhoneNumber(editTextPhoneNumber.getText().toString());
                        user.setDateOfBirth(editTextDateOfBirth.getText().toString());
                        user.setEmployeeNumber(Integer.parseInt(editTextEmployeeNumber.getText().toString()));
                        userDatabase.dao().addUser(user);
                        Toast.makeText(getBaseContext(),"User added successfully!", Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    Toast.makeText(getBaseContext(),"A user already exists with this username!", Toast.LENGTH_SHORT).show();
                    util.formatInvalidInput(editTextUsername);
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUsername.setText("");
                editTextPassword.setText("");
                editTextDateOfBirth.setText("");
                editTextEmployeeNumber.setText("");
                editTextPhoneNumber.setText("");
                editTextAddress.setText("");
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

