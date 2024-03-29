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
    //  create class variables
    public static ItemDatabase itemDatabase;
    private Integer pageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_status);
        itemDatabase = Room.databaseBuilder(getApplicationContext(), ItemDatabase.class, getString(R.string.itemDatabase)).allowMainThreadQueries().build();

        //  get intent from previous activity
        Intent i = getIntent();
        String username = i.getStringExtra(getString(R.string.username));

        //  create variables
        final TextView textViewOutput = findViewById(R.id.textViewInventoryStatus_output);
        final Button buttonInventoryMenu = findViewById(R.id.buttonInventoryStatus_InventoryMenu);
        final Button buttonNext = findViewById(R.id.buttonInventoryStatus_next);
        final Button buttonPrevious = findViewById(R.id.buttonInventoryStatus_previous);
        final Button buttonLogout = findViewById(R.id.buttonInventoryStatus_logout);
        List<Item> items = itemDatabase.dao().getItems();

        //  set previous/next button visibility based on page number
        try {
            Integer totalPages = items.size() / 5;
            textViewOutput.setText(updateOutput(pageNumber, totalPages, items));
            if (getPageNumber() == 0) {
                buttonPrevious.setVisibility(View.INVISIBLE);
            }
            else if (getPageNumber() > 0) {
                buttonPrevious.setVisibility(View.VISIBLE);
            }

            if (getPageNumber() >= totalPages) {
                buttonNext.setVisibility(View.INVISIBLE);
            }
            else if(getPageNumber() <= totalPages) {
                buttonNext.setVisibility(View.VISIBLE);
            }

            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getPageNumber() < totalPages) {
                        setPageNumber(getPageNumber() + 1);
                    }
                    textViewOutput.setText(updateOutput(getPageNumber(), totalPages, items));
                    if (getPageNumber() == 0) {
                        buttonPrevious.setVisibility(View.INVISIBLE);
                    }
                    else if (getPageNumber() > 0) {
                        buttonPrevious.setVisibility(View.VISIBLE);
                    }

                    if (getPageNumber() == totalPages - 1) {
                        buttonNext.setVisibility(View.INVISIBLE);
                    }
                    else if(getPageNumber() < totalPages - 1) {
                        buttonNext.setVisibility(View.VISIBLE);
                    }
                }
            });

            buttonPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getPageNumber() > 0) {
                        setPageNumber(getPageNumber() - 1);
                    }
                    textViewOutput.setText(updateOutput(getPageNumber(), totalPages, items));
                    if (getPageNumber() == 0) {
                        buttonPrevious.setVisibility(View.INVISIBLE);
                    }
                    else if (getPageNumber() > 0) {
                        buttonPrevious.setVisibility(View.VISIBLE);
                    }

                    if (getPageNumber() == totalPages - 1) {
                        buttonNext.setVisibility(View.INVISIBLE);
                    }
                    else if(getPageNumber() < totalPages - 1) {
                        buttonNext.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        catch (Exception e) {
            textViewOutput.setText(R.string.noItems);
            buttonPrevious.setVisibility(View.INVISIBLE);
            buttonNext.setVisibility(View.INVISIBLE);
        }

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

    //  updates output depending on page number
    public String updateOutput (Integer pageNumber, Integer totalPages, List<Item> items) {
        String output = "Item\t\t\tQuantity\t\t\tType\n";
        for (int i = pageNumber*5; i < (pageNumber*5) + 5; i++) {
            output += items.get(i).getItemName() + "\t\t\t" + items.get(i).getItemQuantity() + "\t\t\t" + items.get(i).getItemType() + "\n";
        }
        return output;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }


}