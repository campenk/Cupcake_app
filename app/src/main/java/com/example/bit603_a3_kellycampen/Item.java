package com.example.bit603_a3_kellycampen;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Item")
public class Item {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ItemID")
    Integer itemId;

    @ColumnInfo(name = "ItemName")
    String itemName;

    @ColumnInfo(name = "ItemQuantity")
    Integer itemQuantity;

    @ColumnInfo(name = "ItemType")
    String itemType;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
