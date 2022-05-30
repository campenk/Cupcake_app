package com.example.bit603_a3_kellycampen;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface ItemDao {

    @Insert
    public void addItem(Item item);

    @Query("SELECT * FROM Item")
    public List<Item> getItems();
}
