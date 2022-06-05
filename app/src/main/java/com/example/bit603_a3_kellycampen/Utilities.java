package com.example.bit603_a3_kellycampen;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.ArrayRes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Utilities {

    private static String TAG = "Campen";
    public void formatInvalidInput (EditText editText) {
        editText.setText("");
        editText.setHintTextColor(Color.RED);
    }

    public void checkValidString (EditText editText, HashMap<EditText, String> requiredField) {
        String input = editText.getText().toString();
        if (input.equals("")) {
            formatInvalidInput(editText);
            requiredField.put(editText, input);
        }
        else {
            requiredField.put(editText, null);
        }
    }

    public Integer checkValidInteger (EditText editText, HashMap<EditText, String> requiredFieldsValid) {
       Integer input = null;
        try{
           input = Integer.parseInt(editText.getText().toString());
            requiredFieldsValid.put(editText, input.toString());
        }
       catch (Exception e) {
           formatInvalidInput(editText);
           requiredFieldsValid.put(editText, null);
       }
        return null;
    }

public String checkValidDate (String date, EditText editText, HashMap<EditText, Boolean> requiredFieldsValid) {
    String input = editText.getText().toString();
    if (!input.equals("")) {
        if (isDateValid(input)) {
            requiredFieldsValid.put(editText, true);
            return input;
        }
    }
    else {
        Log.d(TAG, "input is null");
        formatInvalidInput(editText);
        Log.d(TAG, "format invalid input");
        requiredFieldsValid.put(editText, false);
        Log.d(TAG, "put false into hashmap");
    }
    return null;
    }

    public void hideKeyboard(EditText editText) {

        if (Build.VERSION.SDK_INT >= 21) {
            editText.setShowSoftInputOnFocus(false);
        } else if (Build.VERSION.SDK_INT >= 11) {
            editText.setRawInputType(InputType.TYPE_NULL);
            editText.setTextIsSelectable(true);
        } else {
            editText.setRawInputType(InputType.TYPE_NULL);
            editText.isFocusable();
        }

    }

    public Boolean isDateValid (String dateString) {

    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setLenient(false);
        try {
        sdf.parse(dateString);
    } catch (
    ParseException e) {
        return false;
    }
        return true;
}

}
