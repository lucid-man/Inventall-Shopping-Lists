package com.inventall.shoppinglists.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SavedItemDAO {

    @Insert
    public void insert(SavedItem savedItem);

    @Update
    public void update(SavedItem savedItem);

    @Delete
    public void delete(SavedItem savedItem);

    @Query("SELECT * FROM saved_item_table ORDER BY timesUsed DESC")
    public LiveData<List<SavedItem>> getAllSavedItems();

    @Query("SELECT * FROM saved_item_table WHERE id = :id LIMIT 1")
    public LiveData<SavedItem> getSavedItem(int id);

}
