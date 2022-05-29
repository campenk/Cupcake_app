package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageInventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_inventory);

        Button buttonInventoryStatus = findViewById(R.id.buttonManageInventory_status);
        Button buttonAddItem = findViewById(R.id.buttonManageInventory_add);
        Button buttonClearItems = findViewById(R.id.buttonManageInventory_clear);
        Button buttonTestItems = findViewById(R.id.buttonManageInventory_test);

        buttonInventoryStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), InventoryStatusActivity.class);
                startActivity(i);
            }
        });

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddItemActivity.class);
                startActivity(i);
            }
        });

        buttonClearItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent i = new Intent(getApplicationContext(), AddItemActivity.class);
             //   startActivity(i);
            }
        });

        buttonTestItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Intent i = new Intent(getApplicationContext(), AddItemActivity.class);
            //    startActivity(i);
            }
        });
    }
}