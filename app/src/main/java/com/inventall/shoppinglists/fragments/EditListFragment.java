package com.inventall.shoppinglists.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.inventall.shoppinglists.R;
import com.inventall.shoppinglists.models.DatabaseViewModel;
import com.inventall.shoppinglists.roomdatabase.ShoppingList;

public class EditListFragment extends Fragment {


    private DatabaseViewModel dbViewModel;
    private int shoppingListId;
    private ShoppingList shoppingList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shoppingListId = EditListFragmentArgs.fromBundle(getArguments()).getShoppingListId();

        dbViewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);

        EditText editTextName = view.findViewById(R.id.edit_list_edittext_name);

        dbViewModel.getShoppingList(shoppingListId).observe(getViewLifecycleOwner(), list -> {
            shoppingList = list;
            editTextName.setText(shoppingList.getName());
        });


        Button btnSave = view.findViewById(R.id.edit_list_btn_save);
        Button btnSoftDelete = view.findViewById(R.id.edit_list_btn_delete);

        btnSave.setOnClickListener(v -> {
            if (editTextName.getText().toString().isEmpty()) {
                Toast.makeText(requireContext(), R.string.list_name_empty_message, Toast.LENGTH_LONG).show();
                return;
            }
            shoppingList.setName(editTextName.getText().toString());
            dbViewModel.updateShoppingList(shoppingList);

            NavDirections action = EditListFragmentDirections
                    .actionEditListFragmentToShoppingListsFragment();
            Navigation.findNavController(view).navigate(action);
        });


        btnSoftDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.delete_list)
                    .setMessage(R.string.hard_delete_list_message)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbViewModel.deleteShoppingList(shoppingList);
                            NavDirections action = EditListFragmentDirections
                                    .actionEditListFragmentToShoppingListsFragment();
                            Navigation.findNavController(view).navigate(action);
                        }
                    })
                    .setNegativeButton(R.string.no, null)
                    .setIcon(R.drawable.ic_baseline_warning_24)
                    .show();
        });

    }
}
