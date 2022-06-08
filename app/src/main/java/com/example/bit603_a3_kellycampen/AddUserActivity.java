package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AddUserActivity extends AppCompatActivity {
    //  create class variables
    public static UserDatabase_v2 userDatabase;
    private DatePickerDialog picker;
    final Calendar cldr = Calendar.getInstance();
    int day = cldr.get(Calendar.DAY_OF_MONTH);
    int month = cldr.get(Calendar.MONTH);
    private String dateOfBirth;
    private Utilities util = new Utilities();
    private UserList userList = new UserList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase_v2.class, getString(R.string.userDatabase)).allowMainThreadQueries().build();

        //  get intent from previous activity
        Intent i = getIntent();
        String username = i.getStringExtra(getString(R.string.username));

        //  create variables
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

                //  check if user already exists
                if (userList.userExists(editTextUsername.getText().toString(), users)) {
                    Toast.makeText(getBaseContext(), getString(R.string.userAlreadyExists), Toast.LENGTH_SHORT).show();
                    util.formatInvalidInput(editTextUsername);
                }

                else {
                    User user = new User();

                    //  check required fields are filled
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

                    if (util.checkValidDate(editTextDateOfBirth, requiredFields)) {
                        user.setDateOfBirth(editTextDateOfBirth.getText().toString());
                    }

                    //  check all required fields are valid
                    if (!requiredFields.containsValue(null)) {
                        //  add user to database
                        userDatabase.dao().addUser(user);
                        Toast.makeText(getBaseContext(),getString(R.string.userAdded), Toast.LENGTH_SHORT).show();
                        }
                    else {
                        Toast.makeText(getBaseContext(),getString(R.string.invalidInputs), Toast.LENGTH_SHORT).show();
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

