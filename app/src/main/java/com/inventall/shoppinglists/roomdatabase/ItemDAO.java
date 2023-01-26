package com.inventall.shoppinglists.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDAO {
    @Insert
    void insert(Item item);

    @Update
    void update(Item item);

    @Delete
    void delete(Item item);

    @Query("SELECT * FROM item_table WHERE shoppingListId = :id")
    LiveData<List<Item>> getItemsOfList(int id);

    @Query("SELECT * FROM item_table WHERE shoppingListId = :id ORDER BY category ASC")
    LiveData<List<Item>> getItemsByCategoryOfList(int id);
}
