package com.example.bit603_a3_kellycampen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class InventoryStatusActivity extends AppCompatActivity {
    public static ItemDatabase itemDatabase;
    private Integer pageNumber = 0;
    private static String TAG = "Campen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_status);
        itemDatabase = Room.databaseBuilder(getApplicationContext(), ItemDatabase.class, "itemdb").allowMainThreadQueries().build();

        final TextView textViewOutput = findViewById(R.id.textViewInventoryStatus_output);
        final Button buttonInventoryMenu = findViewById(R.id.buttonInventoryStatus_InventoryMenu);
        final Button buttonNext = findViewById(R.id.buttonInventoryStatus_next);
        final Button buttonPrevious = findViewById(R.id.buttonInventoryStatus_previous);

        List<Item> items = itemDatabase.dao().getItems();
        Log.d(TAG, "items is null");

        Integer pageNumber = 0;
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
                        Log.d(TAG, "setNext button invisible");
                        buttonNext.setVisibility(View.INVISIBLE);
                    }
                    else if(getPageNumber() < totalPages - 1) {
                        Log.d(TAG, "setNext button visible");
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
                        Log.d(TAG, "setNext button invisible");
                        buttonNext.setVisibility(View.INVISIBLE);
                    }
                    else if(getPageNumber() < totalPages - 1) {
                        Log.d(TAG, "setNext button visible");
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
                startActivity(i);
            }
        });

    }
//  TODO: Check what happens if result is decimal
    public String updateOutput (Integer pageNumber, Integer totalPages, List<Item> items) {
        String output = "Item\t\t\tQuantity\t\t\tType\n";
        for (int i = pageNumber*5; i < (pageNumber*5) + 5; i++) {
            Log.d(TAG, "i = " + i);
            output += items.get(i).getItemName() + "\t\t\t" + items.get(i).getItemQuantity() + "\t\t\t" + items.get(i).getItemType() + "\n";
        }
        Log.d(TAG, "Page number = " + getPageNumber().toString());
        Log.d(TAG, "Total pages = " + totalPages.toString());
        return output;
    }



    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }


}