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

    //  create class variables
    public static ItemDatabase itemDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        itemDatabase = Room.databaseBuilder(getApplicationContext(), ItemDatabase.class, getString(R.string.itemDatabase)).allowMainThreadQueries().build();

        //  get intent from previous activity
        Intent i = getIntent();
        String username =i.getStringExtra(getString(R.string.username));

        //  create variables
        final EditText editTextItemName = findViewById(R.id.editTextAddItem_name);
        final EditText editTextItemQuantity = findViewById(R.id.editTextAddItem_quantity);
        final Button buttonSubmit = findViewById(R.id.buttonAddItem_Submit);
        final Button buttonInventoryMenu = findViewById(R.id.buttonAddItem_InventoryMenu);
        final Button buttonLogout = findViewById(R.id.buttonAddItem_logout);

        //  Populate item type spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinnerAddItem_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.itemTypeArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<EditText, String> requiredFields = new HashMap<>();
                Utilities util = new Utilities();
                Item item = new Item();

                //  check valid inputs
                if (util.checkValidString(editTextItemName, requiredFields)) {
                    item.setItemName(editTextItemName.getText().toString());
                }

                if (util.checkValidInteger(editTextItemQuantity, requiredFields)) {
                    item.setItemQuantity(Integer.parseInt(editTextItemName.getText().toString()));
                }

                if (util.checkValidInteger(editTextItemQuantity, requiredFields)) {
                    item.setItemQuantity(Integer.parseInt(editTextItemName.getText().toString()));
                }

                String itemType = spinner.getSelectedItem().toString();
                if (itemType.equals(getString(R.string.biscuit)) || itemType.equals(getString(R.string.cookie)) || itemType.equals(getString(R.string.cake)) || itemType.equals(getString(R.string.ingredient)) || itemType.equals(getString(R.string.other))) {
                    item.setItemType(itemType);
                }

                //  check all required fields are filled
                if (!requiredFields.containsValue(null)) {
                    //  add item to database
                    itemDatabase.dao().addItem(item);
                    Toast.makeText(getBaseContext(),getString(R.string.itemAdded), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getBaseContext(),getString(R.string.invalidInputs), Toast.LENGTH_SHORT).show();
                }

                //  reset entries
                editTextItemName.setText("");
                editTextItemQuantity.setText("");
            }
        });

        buttonInventoryMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ManageInventoryActivity.class);
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
}