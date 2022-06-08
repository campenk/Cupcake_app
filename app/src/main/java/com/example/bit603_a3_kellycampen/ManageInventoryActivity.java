package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageInventoryActivity extends AppCompatActivity {
    //  create class variables
    public static ItemDatabase itemDatabase;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_inventory);
        itemDatabase = Room.databaseBuilder(getApplicationContext(), ItemDatabase.class, getString(R.string.itemDatabase)).allowMainThreadQueries().build();

        //  get intent from previous activity
        Intent i = getIntent();
        String username = i.getStringExtra(getString(R.string.username));

        //  create variables
        final Button buttonInventoryStatus = findViewById(R.id.buttonManageInventory_status);
        final Button buttonAddItem = findViewById(R.id.buttonManageInventory_add);
        final Button buttonClearItems = findViewById(R.id.buttonManageInventory_clear);
        final Button buttonTestItems = findViewById(R.id.buttonManageInventory_test);
        final Button buttonUserMenu = findViewById(R.id.buttonManageInventory_UserMenu);
        final Button buttonLogout = findViewById(R.id.buttonManageInventory_logout);

        //  allow admin to access the Manage User menu
        if (username.equals(getString(R.string.adminusername))) {
            buttonUserMenu.setVisibility(View.VISIBLE);
        }
        else {
            buttonUserMenu.setVisibility(View.GONE);
        }

        buttonInventoryStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), InventoryStatusActivity.class);
                i.putExtra(getString(R.string.username), username);
                startActivity(i);
            }
        });

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddItemActivity.class);
                i.putExtra(getString(R.string.username), username);
                startActivity(i);
            }
        });

        buttonClearItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageInventoryActivity.this);
                builder.setMessage(getString(R.string.clearDatabaseConfirmation))
                        .setTitle(getString(R.string.confirmationRequired));
                builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        itemDatabase.dao().clearAllItems();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog = builder.create();

                dialog.show();
            }
        });

        buttonTestItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageInventoryActivity.this);
                builder.setMessage(getString(R.string.addTestItemsConfirmation))
                        .setTitle(getString(R.string.confirmationRequired));

                builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Utilities util = new Utilities();
                        util.addTestItems(itemDatabase);
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog = builder.create();

                dialog.show();
            }
        });

        buttonUserMenu.setOnClickListener(new View.OnClickListener() {
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


}