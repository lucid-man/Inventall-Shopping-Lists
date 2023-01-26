package com.inventall.shoppinglists.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ShoppingList.class, Item.class, SavedItem.class}, version = 1, exportSchema = true)
public abstract class ROOMDatabase extends RoomDatabase {

    public abstract ShoppingListDAO shoppingListDAO();
    public abstract ItemDAO itemDAO();
    public abstract SavedItemDAO savedItemDAO();

    private static volatile ROOMDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ROOMDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ROOMDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                ROOMDatabase.class, "shopping_lists_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
