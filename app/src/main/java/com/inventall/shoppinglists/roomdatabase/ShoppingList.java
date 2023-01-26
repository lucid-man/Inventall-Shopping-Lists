package com.inventall.shoppinglists.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "shopping_list_table")
public class ShoppingList {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @NonNull
    private String name;

    public ShoppingList(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
