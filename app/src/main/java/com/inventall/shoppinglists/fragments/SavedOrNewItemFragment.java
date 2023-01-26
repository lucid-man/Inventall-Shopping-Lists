package com.inventall.shoppinglists.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inventall.shoppinglists.R;
import com.inventall.shoppinglists.adapters.SavedItemsAdapter;
import com.inventall.shoppinglists.models.DatabaseViewModel;
import com.inventall.shoppinglists.roomdatabase.SavedItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SavedOrNewItemFragment extends Fragment {

    private int shoppingListId;
    private List<SavedItem> allSavedItems;

    private DatabaseViewModel dbViewModel;
    private RecyclerView recyclerSaved;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saved_or_new_item, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shoppingListId = SavedOrNewItemFragmentArgs.fromBundle(getArguments()).getShoppingListId();

        dbViewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);
        dbViewModel.getAllSavedItems().observe(getViewLifecycleOwner(), items -> {
            allSavedItems = items;
            setRecyclerAdapter(allSavedItems);
        });

        EditText edittextName = view.findViewById(R.id.saved_or_new_edittext_name);
        FloatingActionButton fabNew = view.findViewById(R.id.saved_or_new_fab_new);
        recyclerSaved = view.findViewById(R.id.saved_or_new_recycler_saved);

        recyclerSaved.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        edittextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterSavedItems(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        fabNew.setOnClickListener(v -> {
            if (edittextName.getText().toString().isEmpty()) {
                Toast.makeText(requireContext(), R.string.item_name_empty_message, Toast.LENGTH_LONG).show();
                return;
            }
            String itemName = edittextName.getText().toString();
            NavDirections action = SavedOrNewItemFragmentDirections
                    .actionSavedOrNewItemFragmentToAddNewItemFragment(shoppingListId, itemName);
            Navigation.findNavController(view).navigate(action);

        });
    }

    private void setRecyclerAdapter(List<SavedItem> savedItems) {
        recyclerSaved.setAdapter(new SavedItemsAdapter(savedItems, shoppingListId, dbViewModel));
    }

    private void filterSavedItems(String chars) {
        List<SavedItem> newList = new ArrayList<>();
        for (int i = 0; i < allSavedItems.size(); i++) {
            if (allSavedItems.get(i).getName().toLowerCase().startsWith(chars.toLowerCase())) {
                newList.add(allSavedItems.get(i));
            }
        }
        setRecyclerAdapter(newList);
    }
}
