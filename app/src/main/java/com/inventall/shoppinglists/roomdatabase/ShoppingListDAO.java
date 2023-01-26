package com.inventall.shoppinglists.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShoppingListDAO {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insert(ShoppingList shoppingList);

    @Update
    void update(ShoppingList shoppingList);

    @Delete
    void delete(ShoppingList shoppingList);

    @Query("SELECT * FROM shopping_list_table ORDER BY name ASC")
    LiveData<List<ShoppingList>> getAlphabetizedShoppingLists();

    @Query("SELECT * FROM shopping_list_table WHERE id = :id LIMIT 1")
    LiveData<ShoppingList> getShoppingList(int id);

}
