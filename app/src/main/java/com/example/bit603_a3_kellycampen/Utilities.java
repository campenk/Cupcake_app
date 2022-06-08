package com.example.bit603_a3_kellycampen;

import android.graphics.Color;
import android.os.Build;
import android.text.InputType;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Utilities {

    //  sets EditText text colour to red and clears text
    public void formatInvalidInput (EditText editText) {
        editText.setText("");
        editText.setHintTextColor(Color.RED);
    }

    //  sets EditText text colour to black and clears text
    public void formatReset (EditText editText) {
        editText.setText("");
        editText.setHintTextColor(Color.BLACK);
    }

    //  if string is null, formats EditText and puts null into Hashmap. If string is not null, puts string into Hashmap
    public Boolean checkValidString (EditText editText, HashMap<EditText, String> requiredField) {
        String input = editText.getText().toString();
        if (input.equals("")) {
            formatInvalidInput(editText);
            requiredField.put(editText, null);
            return false;
        }
        else {
            requiredField.put(editText, input);
            return true;
        }
    }

    //  if input is not Integer, formats EditText and puts null into Hashmap. If input is Integer, puts Integer into Hashmap as string
    public Boolean checkValidInteger (EditText editText, HashMap<EditText, String> requiredFieldsValid) {
       Integer input = null;
        try{
           input = Integer.parseInt(editText.getText().toString());
            requiredFieldsValid.put(editText, input.toString());
            return true;
        }
       catch (Exception e) {
           formatInvalidInput(editText);
           requiredFieldsValid.put(editText, null);
           return false;
       }
    }

    //  if input is null, formats EditText and puts null into Hashmap. If input is in invalid format, formats EditText and puts null into Hashmap. If
    //  input is valid, puts String into Hashmap
    public Boolean checkValidDate (EditText editText, HashMap<EditText, String> requiredFieldsValid) {
        String input = editText.getText().toString();
        if (input.equals("") || input.equals(null)) {
            formatInvalidInput(editText);
            requiredFieldsValid.put(editText, null);
            return false;
        }
        else if (isDateValid(input)) {
                requiredFieldsValid.put(editText, input);
               return true;
            }
        else {
            requiredFieldsValid.put(editText, null);
            formatInvalidInput(editText);
            return false;
        }

    }

    //  hides the soft keyboard
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

    //  checks date is in valid format
    public Boolean isDateValid (String dateString) {
    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
        sdf.parse(dateString);
    } catch (
    ParseException e) {
        return false;
    }
        return true;
    }

    public void addTestItems (ItemDatabase itemDatabase) {
        Item flour = new Item();
        flour.setItemName("flour");
        flour.setItemQuantity(1);
        flour.setItemType("ingredient");
        itemDatabase.dao().addItem(flour);

        Item sugar = new Item();
        sugar.setItemName("sugar");
        sugar.setItemQuantity(2);
        sugar.setItemType("ingredient");
        itemDatabase.dao().addItem(sugar);

        Item milk = new Item();
        milk.setItemName("milk");
        milk.setItemQuantity(3);
        milk.setItemType("ingredient");
        itemDatabase.dao().addItem(milk);

        Item eggs = new Item();
        eggs.setItemName("eggs");
        eggs.setItemQuantity(4);
        eggs.setItemType("ingredient");
        itemDatabase.dao().addItem(eggs);

        Item bakingPowder = new Item();
        bakingPowder.setItemName("bakingPowder");
        bakingPowder.setItemQuantity(5);
        bakingPowder.setItemType("ingredient");
        itemDatabase.dao().addItem(bakingPowder);

        Item bananas = new Item();
        bananas.setItemName("bananas");
        bananas.setItemQuantity(6);
        bananas.setItemType("ingredient");
        itemDatabase.dao().addItem(bananas);

        Item apples = new Item();
        apples.setItemName("apples");
        apples.setItemQuantity(7);
        apples.setItemType("ingredient");
        itemDatabase.dao().addItem(apples);

        Item anzacBiscuits = new Item();
        anzacBiscuits.setItemName("anzac biscuit");
        anzacBiscuits.setItemQuantity(8);
        anzacBiscuits.setItemType("Biscuit");
        itemDatabase.dao().addItem(anzacBiscuits);

        Item afghanBiscuit = new Item();
        afghanBiscuit.setItemName("afghan biscuit");
        afghanBiscuit.setItemQuantity(9);
        afghanBiscuit.setItemType("Biscuit");
        itemDatabase.dao().addItem(afghanBiscuit);

        Item yoyoBiscuit = new Item();
        yoyoBiscuit.setItemName("yoyo biscuit");
        yoyoBiscuit.setItemQuantity(10);
        yoyoBiscuit.setItemType("Biscuit");
        itemDatabase.dao().addItem(yoyoBiscuit);

        Item chocolateChipCookie = new Item();
        chocolateChipCookie.setItemName("chocolate chip cookie");
        chocolateChipCookie.setItemQuantity(11);
        chocolateChipCookie.setItemType("Cookie");
        itemDatabase.dao().addItem(chocolateChipCookie);

        Item oatmealRaisinCookie = new Item();
        oatmealRaisinCookie.setItemName("oatmeal raisin cookie");
        oatmealRaisinCookie.setItemQuantity(12);
        oatmealRaisinCookie.setItemType("Cookie");
        itemDatabase.dao().addItem(oatmealRaisinCookie);

        Item doubleChocolateCookie = new Item();
        doubleChocolateCookie.setItemName("double chocolate cookie");
        doubleChocolateCookie.setItemQuantity(13);
        doubleChocolateCookie.setItemType("Cookie");
        itemDatabase.dao().addItem(doubleChocolateCookie);

        Item lemonCake = new Item();
        lemonCake.setItemName("lemon cake");
        lemonCake.setItemQuantity(14);
        lemonCake.setItemType("Cake");
        itemDatabase.dao().addItem(lemonCake);

        Item chocolateCake = new Item();
        chocolateCake.setItemName("chocolate cake");
        chocolateCake.setItemQuantity(15);
        chocolateCake.setItemType("Cake");
        itemDatabase.dao().addItem(chocolateCake);

        Item vanillaCake = new Item();
        vanillaCake.setItemName("vanilla cake");
        vanillaCake.setItemQuantity(16);
        vanillaCake.setItemType("Cake");
        itemDatabase.dao().addItem(vanillaCake);

        Item carrotCake = new Item();
        carrotCake.setItemName("carrot cake");
        carrotCake.setItemQuantity(17);
        carrotCake.setItemType("Cake");
        itemDatabase.dao().addItem(carrotCake);

        Item bananaCake = new Item();
        bananaCake.setItemName("banana cake");
        bananaCake.setItemQuantity(18);
        bananaCake.setItemType("Cake");
        itemDatabase.dao().addItem(bananaCake);

        Item appleCake = new Item();
        appleCake.setItemName("apple cake");
        appleCake.setItemQuantity(19);
        appleCake.setItemType("Cake");
        itemDatabase.dao().addItem(appleCake);

        Item cheesecake = new Item();
        cheesecake.setItemName("cheesecake");
        cheesecake.setItemQuantity(20);
        cheesecake.setItemType("Cake");
        itemDatabase.dao().addItem(cheesecake);
    }
}
