package com.inventall.shoppinglists.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.inventall.shoppinglists.roomdatabase.Item;
import com.inventall.shoppinglists.roomdatabase.Repository;
import com.inventall.shoppinglists.roomdatabase.SavedItem;
import com.inventall.shoppinglists.roomdatabase.ShoppingList;

import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {

    private Repository repository;

    private final LiveData<List<ShoppingList>> allShoppingLists;
    private final LiveData<List<SavedItem>> allSavedItems;

    public DatabaseViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        allShoppingLists = repository.getAllShoppingLists();
        allSavedItems = repository.getAllSavedItems();
    }




    public void insertShoppingList(ShoppingList shoppingList) {
        repository.insertShoppingList(shoppingList);
    }
    public void updateShoppingList(ShoppingList shoppingList) {
        repository.updateShoppingList(shoppingList);
    }
    public void deleteShoppingList(ShoppingList shoppingList) {
        repository.deleteShoppingList(shoppingList);
    }
    public LiveData<List<ShoppingList>> getAllShoppingLists() {
        return allShoppingLists;
    }
    public LiveData<ShoppingList> getShoppingList(int id) { return repository.getShoppingList(id); }



    public void insertItem(Item item) {
        repository.insertItem(item);
    }
    public void updateItem(Item item) {
        repository.updateItem(item);
    }
    public void deleteItem(Item item) {
        repository.deleteItem(item);
    }
    public LiveData<List<Item>> getAllItemsFromList(int id) {
        return repository.getItemsFromList(id);
    }
    public LiveData<List<Item>> getAllItemsByCategoryFromList(int id) {
        return repository.getItemsByCategoryFromList(id);
    }



    public void insertSavedItem(SavedItem savedItem) {
        repository.insertSavedItem(savedItem);
    }
    public void updateSavedItem(SavedItem savedItem) {
        repository.updateSavedItem(savedItem);
    }
    public void deleteSavedItem(SavedItem savedItem) {
        repository.deleteSavedItem(savedItem);
    }
    public LiveData<SavedItem> getSavedItem(int id) { return repository.getSavedItem(id); }
    public LiveData<List<SavedItem>> getAllSavedItems() {
        return allSavedItems;
    }
}
