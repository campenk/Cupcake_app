package com.example.bit603_a3_kellycampen;

import android.graphics.Color;
import android.widget.EditText;

public class Utilities {
    public void formatInvalidInput (EditText editText) {
        editText.setText("");
        editText.setHintTextColor(Color.RED);
    }

}
