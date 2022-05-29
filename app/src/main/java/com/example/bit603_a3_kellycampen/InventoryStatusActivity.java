package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class InventoryStatusActivity extends AppCompatActivity {
    public static ItemDatabase itemDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_status);
        itemDatabase = Room.databaseBuilder(getApplicationContext(), ItemDatabase.class, "itemdb").allowMainThreadQueries().build();

        final TextView textViewOutput = findViewById(R.id.textViewInventoryStatus_output);
        final Button buttonInventoryMenu = findViewById(R.id.buttonInventoryStatus_InventoryMenu);

        List<Item> items = itemDatabase.dao().getItems();
        String output = "Item\t\t\tQuantity\t\t\tType\n";

        for (Item item : items) {
            output += item.getItemName() + "\t\t\t" + item.getItemQuantity() + "\t\t\t" + item.getItemType() + "\n";
        }

        textViewOutput.setText(output);

        buttonInventoryMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     Intent i = new Intent(getApplicationContext(), MainActivity.class);
           //     startActivity(i);
            }
        });

    }
}