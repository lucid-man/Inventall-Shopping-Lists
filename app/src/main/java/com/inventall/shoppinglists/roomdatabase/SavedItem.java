package com.inventall.shoppinglists.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "saved_item_table")
public class SavedItem {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String name;
    private int category;
    private int timesUsed;

    public SavedItem(String name, int category, int timesUsed) {
        this.name = name;
        this.category = category;
        this.timesUsed = timesUsed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }

    public int getTimesUsed() {
        return timesUsed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setTimesUsed(int timesUsed) {
        this.timesUsed = timesUsed;
    }
}
