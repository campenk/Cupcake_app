package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
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
        if (Build.VERSION.SDK_INT >= 21) {
            editTextDateOfBirth.setShowSoftInputOnFocus(false);
        } else if (Build.VERSION.SDK_INT >= 11) {
            editTextDateOfBirth.setRawInputType(InputType.TYPE_CLASS_TEXT);
            editTextDateOfBirth.setTextIsSelectable(true);
        } else {
            editTextDateOfBirth.setRawInputType(InputType.TYPE_NULL);
            editTextDateOfBirth.isFocusable();
        }

        //  TODO: Change to when receives focus
        editTextDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                picker.show();
            //    Log.d(TAG, dateOfBirth.toString());
            }

        });



        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<EditText, Boolean> requiredFields = new HashMap<>();
                Utilities util = new Utilities();
                User user = new User();

                //  TODO: prevent duplicate username being added
                util.checkValidString(editTextUsername, requiredFields);
                util.checkValidString(editTextPassword, requiredFields);
                util.checkValidString(editTextAddress, requiredFields);
                util.checkValidString(editTextPhoneNumber, requiredFields);

                //  TODO: Improve method for validating dob input
                util.checkValidString(editTextDateOfBirth, requiredFields);

                util.checkValidInteger(editTextEmployeeNumber, requiredFields);

if (!requiredFields.containsValue(false)) {
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

