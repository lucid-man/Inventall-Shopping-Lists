package com.inventall.shoppinglists.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.inventall.shoppinglists.R;
import com.inventall.shoppinglists.adapters.CategoryAdapter;
import com.inventall.shoppinglists.models.DatabaseViewModel;
import com.inventall.shoppinglists.roomdatabase.Item;
import com.inventall.shoppinglists.roomdatabase.SavedItem;

public class AddNewItemFragment extends Fragment {

    private DatabaseViewModel dbViewModel;

    private String itemName;
    private int itemCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int shoppingListId = AddNewItemFragmentArgs.fromBundle(getArguments()).getShoppingListId();
        itemName = AddNewItemFragmentArgs.fromBundle(getArguments()).getItemName();
        itemCategory = 0;
        dbViewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);

        TextView textItemName = view.findViewById(R.id.add_new_item_text_name);
        Spinner spinnerCategory = view.findViewById(R.id.add_new_item_spinner_category);
        EditText editTextAmount = view.findViewById(R.id.add_new_item_edittext_amount);
        EditText editTextTag = view.findViewById(R.id.add_new_item_edittext_tag);
        Button btnSave = view.findViewById(R.id.add_new_item_btn_save);

        textItemName.setText(itemName);

        String[] categories = getResources().getStringArray(R.array.categories_array);
        spinnerCategory.setAdapter(new CategoryAdapter(getLayoutInflater(), categories));
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemCategory = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSave.setOnClickListener(v -> {

            if (editTextAmount.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), R.string.amount_empty_message, Toast.LENGTH_LONG).show();
                return;
            }
            int itemAmount = Integer.parseInt(editTextAmount.getText().toString());
            String itemTag = editTextTag.getText().toString().isEmpty() ? null: editTextTag.getText().toString();
            Item newItem = new Item(
                    itemName, itemCategory, itemAmount,  itemTag, shoppingListId
            );

            dbViewModel.insertItem(newItem);

            SavedItem savedItem = new SavedItem(itemName, itemCategory, 1);
            dbViewModel.insertSavedItem(savedItem);

            NavDirections action = AddNewItemFragmentDirections
                    .actionAddNewItemFragmentToItemsFragment(shoppingListId);
            Navigation.findNavController(view).navigate(action);
        });
    }
}
