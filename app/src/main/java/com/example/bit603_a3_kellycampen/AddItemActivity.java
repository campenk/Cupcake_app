package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class AddItemActivity extends AppCompatActivity   {
    public static ItemDatabase itemDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        itemDatabase = Room.databaseBuilder(getApplicationContext(), ItemDatabase.class, "itemdb").allowMainThreadQueries().build();

        EditText editTextItemName = findViewById(R.id.editTextAddItem_name);
        EditText editTextItemQuantity = findViewById(R.id.editTextAddItem_quantity);
        Button buttonSubmit = findViewById(R.id.buttonAddItem_Submit);
        Button buttonInventoryMenu = findViewById(R.id.buttonAddItem_InventoryMenu);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerAddItem_type);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.itemTypeArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);




        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<EditText, Boolean> inputIsValid = new HashMap<>();
                inputIsValid.put(editTextItemName, false);
                inputIsValid.put(editTextItemQuantity, false);

                Utilities util = new Utilities();
                util.checkValidString(editTextItemName, inputIsValid);
                util.checkValidInteger(editTextItemQuantity, inputIsValid);



                String itemName = editTextItemName.getText().toString();
                Integer itemQuantity = Integer.parseInt(editTextItemQuantity.getText().toString());

                Item item = new Item();
                item.setItemName(itemName);
                item.setItemQuantity(itemQuantity);

                itemDatabase.dao().addItem(item);

                Toast.makeText(getBaseContext(),"Item added successfully!", Toast.LENGTH_SHORT).show();
                editTextItemName.setText("");
                editTextItemQuantity.setText("");
            }
        });

        buttonInventoryMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent i = new Intent(getApplicationContext(), MainActivity.class);
              //  startActivity(i);
            }
        });



    }
}