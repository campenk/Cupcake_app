package com.example.bit603_a3_kellycampen;

import android.graphics.Color;
import android.util.Log;
import android.widget.EditText;

import java.util.HashMap;

public class Utilities {
    private static String TAG = "Campen";
    public void formatInvalidInput (EditText editText) {
        editText.setText("");
        editText.setHintTextColor(Color.RED);
    }

    public void checkValidString (EditText editText, HashMap<EditText, Boolean> requiredFieldsValid) {
        String input = editText.getText().toString();
        if (input.equals("")) {
            Log.d(TAG, "input is null");
            formatInvalidInput(editText);
            Log.d(TAG, "format invalid input");
            requiredFieldsValid.put(editText, false);
            Log.d(TAG, "put false into hashmap");
        }
        else {
            requiredFieldsValid.put(editText, true);
            Log.d(TAG, "put true into hashmap");

        }
    }

    public void checkValidInteger (EditText editText, HashMap<EditText, Boolean> requiredFieldsValid) {
       Integer input;
        try{
           input = Integer.parseInt(editText.getText().toString());
            requiredFieldsValid.put(editText, true);

        }
       catch (Exception e) {
           Log.d(TAG, "input is null");
           formatInvalidInput(editText);
           Log.d(TAG, "format invalid input");
           requiredFieldsValid.put(editText, false);
           Log.d(TAG, "put false into hashmap");
       }
    }




}
