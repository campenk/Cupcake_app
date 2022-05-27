package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {
    public static ItemDatabase itemDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        itemDatabase = Room.databaseBuilder(getApplicationContext(), ItemDatabase.class, "itemdb").allowMainThreadQueries().build();

        EditText editTextItemName = findViewById(R.id.editTextAddItem_name);
        EditText editTextItemQuantity = findViewById(R.id.editTextAddItem_quantity);
        EditText editTextItemType = findViewById(R.id.editTextAddItem_type);
        Button buttonSubmit = findViewById(R.id.buttonAddItem_Submit);
        Button buttonInventoryMenu = findViewById(R.id.buttonAddItem_InventoryMenu);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = editTextItemName.getText().toString();
                Integer itemQuantity = Integer.parseInt(editTextItemQuantity.getText().toString());
                String itemType = editTextItemType.getText().toString();

                Item item = new Item();
                item.setItemName(itemName);
                item.setItemQuantity(itemQuantity);
                item.setItemType(itemType);

                itemDatabase.dao().addItem(item);

                Toast.makeText(getBaseContext(),"Item added successfully!", Toast.LENGTH_SHORT).show();
                editTextItemName.setText("");
                editTextItemQuantity.setText("");
                editTextItemType.setText("");
            }
        });

        buttonInventoryMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });




    }
}