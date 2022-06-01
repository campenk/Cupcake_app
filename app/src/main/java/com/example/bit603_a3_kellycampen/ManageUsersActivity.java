package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        Button buttonAddUser = findViewById(R.id.buttonManageUsers_AddUser);
        Button buttonViewUsers = findViewById(R.id.buttonManageUsers_viewUsers);
        Button buttonRemoveUsers = findViewById(R.id.buttonManageUsers_removeUser);
        Button buttonManageInventory = findViewById(R.id.buttonManageUsers_Inventory);

        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddUserActivity.class);
                startActivity(i);
            }
        });

        buttonViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ViewUsersActivity.class);
                startActivity(i);
            }
        });

        buttonRemoveUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(), RemoveUserActivity.class);
                startActivity(i);
            }
        });

        buttonManageInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ManageInventoryActivity.class);
                startActivity(i);
            }
        });
    }
}