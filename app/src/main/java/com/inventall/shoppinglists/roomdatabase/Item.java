package com.inventall.shoppinglists.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_table", foreignKeys = @ForeignKey( entity = ShoppingList.class,
                                                                parentColumns = "id",
                                                                childColumns = "shoppingListId",
                                                                onDelete = ForeignKey.CASCADE))
public class Item {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    private String name;
    private int category;
    private int amount;
    private String tag;

    @ColumnInfo(index = true)
    private int shoppingListId;

    public Item(String name, int category, int amount, String tag, int shoppingListId) {
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.tag = tag;
        this.shoppingListId = shoppingListId;
    }

    public void setId(int id) { this.id = id; }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public String getTag() {
        return tag;
    }

    public int getShoppingListId() {
        return shoppingListId;
    }
}
