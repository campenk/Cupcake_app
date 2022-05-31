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
    public static ItemDatabase itemDatabase;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_inventory);
        itemDatabase = Room.databaseBuilder(getApplicationContext(), ItemDatabase.class, "itemdb").allowMainThreadQueries().build();


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
                // Instantiate
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageInventoryActivity.this);
                // Set characteristics
                builder.setMessage("Do you want to add clear all items from the database?")
                        .setTitle("Confirmation required");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        itemDatabase.dao().clearAllItems();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Cancel button was clicked
                    }
                });
                // Get AlertDialog
                AlertDialog dialog = builder.create();

                // Show dialog
                dialog.show();
            }
        });

        buttonTestItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageInventoryActivity.this);
                // Set characteristics
                builder.setMessage("Do you want to add test items?")
                        .setTitle("Confirmation required");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        addTestItems();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Cancel button was clicked
                    }
                });
                // Get AlertDialog
                AlertDialog dialog = builder.create();

                // Show dialog
                dialog.show();
            }
        });
    }

    private void addTestItems () {
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