package com.inventall.shoppinglists.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.inventall.shoppinglists.Constants;
import com.inventall.shoppinglists.R;
import com.inventall.shoppinglists.models.DatabaseViewModel;
import com.inventall.shoppinglists.roomdatabase.Item;
import com.inventall.shoppinglists.roomdatabase.SavedItem;

public class AddSavedItemFragment extends Fragment {


    private DatabaseViewModel dbViewModel;
    private SavedItem savedItem;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_saved_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbViewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);


        int shoppingListId = AddSavedItemFragmentArgs.fromBundle(getArguments()).getShoppingListId();
        int savedItemId = AddSavedItemFragmentArgs.fromBundle(getArguments()).getSavedItemId();

        TextView textName = view.findViewById(R.id.add_saved_item_text_name);
        ImageView imageCategory = view.findViewById(R.id.add_saved_item_image_category);
        TextView textCategory = view.findViewById(R.id.add_saved_item_text_category);

        String[] categories = getResources().getStringArray(R.array.categories_array);

        dbViewModel.getSavedItem(savedItemId).observe(getViewLifecycleOwner(), sItem -> {
            savedItem = sItem;
            textName.setText(savedItem.getName());
            imageCategory.setImageResource(Constants.categoriesImages[savedItem.getCategory()]);
            textCategory.setText(categories[savedItem.getCategory()]);
        });


        EditText editTextAmount = view.findViewById(R.id.add_saved_item_edittext_amount);
        EditText editTextTag = view.findViewById(R.id.add_saved_item_edittext_tag);
        Button btnSave = view.findViewById(R.id.add_saved_item_btn_save);

        btnSave.setOnClickListener(v -> {

            if (editTextAmount.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), R.string.amount_empty_message, Toast.LENGTH_LONG).show();
                return;
            }
            int itemAmount = Integer.parseInt(editTextAmount.getText().toString());
            String itemTag = editTextTag.getText().toString().isEmpty() ? null: editTextTag.getText().toString();
            Item newItem = new Item(
                    savedItem.getName(), savedItem.getCategory(), itemAmount,  itemTag, shoppingListId
            );

            dbViewModel.insertItem(newItem);

            savedItem.setTimesUsed(savedItem.getTimesUsed() + 1);

            dbViewModel.updateSavedItem(savedItem);

            NavDirections action = AddSavedItemFragmentDirections
                    .actionAddSavedItemFragmentToItemsFragment(shoppingListId);
            Navigation.findNavController(view).navigate(action);

        });

    }
}
