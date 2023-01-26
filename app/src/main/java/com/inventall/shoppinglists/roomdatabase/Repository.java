package com.inventall.shoppinglists.roomdatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    private ShoppingListDAO shoppingListDAO;

    private ItemDAO itemDAO;

    private SavedItemDAO savedItemDAO;

    public Repository(Application application) {
        ROOMDatabase db = ROOMDatabase.getDatabase(application);
        shoppingListDAO = db.shoppingListDAO();
        itemDAO = db.itemDAO();
        savedItemDAO = db.savedItemDAO();
    }





    public void insertShoppingList(ShoppingList shoppingList) {
        ROOMDatabase.databaseWriteExecutor.execute(() -> {
            shoppingListDAO.insert(shoppingList);
        });
    }

    public void updateShoppingList(ShoppingList shoppingList) {
        ROOMDatabase.databaseWriteExecutor.execute(() -> {
            shoppingListDAO.update(shoppingList);
        });
    }

    public void deleteShoppingList(ShoppingList shoppingList) {
        ROOMDatabase.databaseWriteExecutor.execute(() -> {
            shoppingListDAO.delete(shoppingList);
        });
    }


    public LiveData<List<ShoppingList>> getAllShoppingLists() {
        return shoppingListDAO.getAlphabetizedShoppingLists();
    }

    public LiveData<ShoppingList> getShoppingList(int id) {
        return shoppingListDAO.getShoppingList(id);
    }




    public void insertItem(Item item) {
        ROOMDatabase.databaseWriteExecutor.execute(() -> {
            itemDAO.insert(item);
        });
    }

    public void updateItem(Item item) {
        ROOMDatabase.databaseWriteExecutor.execute(() -> {
            itemDAO.update(item);
        });
    }

    public void deleteItem(Item item) {
        ROOMDatabase.databaseWriteExecutor.execute(() -> {
            itemDAO.delete(item);
        });
    }


    public LiveData<List<Item>> getItemsFromList(int id) {
        return itemDAO.getItemsOfList(id);
    }

    public LiveData<List<Item>> getItemsByCategoryFromList(int id) {
        return itemDAO.getItemsByCategoryOfList(id);
    }






    public void insertSavedItem(SavedItem savedItem) {
        ROOMDatabase.databaseWriteExecutor.execute(() -> {
            savedItemDAO.insert(savedItem);
        });
    }
    public void updateSavedItem(SavedItem savedItem) {
        ROOMDatabase.databaseWriteExecutor.execute(() -> {
            savedItemDAO.update(savedItem);
        });
    }
    public void deleteSavedItem(SavedItem savedItem) {
        ROOMDatabase.databaseWriteExecutor.execute(() -> {
            savedItemDAO.delete(savedItem);
        });
    }

    public LiveData<SavedItem> getSavedItem(int id) { return savedItemDAO.getSavedItem(id); }
    public LiveData<List<SavedItem>> getAllSavedItems() {
        return savedItemDAO.getAllSavedItems();
    }
}





