package com.inventall.shoppinglists.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class CreateListFragment extends Fragment {

    DatabaseViewModel dbViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbViewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);



        EditText name = view.findViewById(R.id.create_list_edit_text_name);
        Button btnCreate = view.findViewById(R.id.create_list_btn_create);


        btnCreate.setOnClickListener(v -> {
//            SAVE the list
//
//            navigate back to list
            if (name.getText().toString().isEmpty()) {
                Toast.makeText(requireContext(), R.string.list_name_empty_message, Toast.LENGTH_LONG).show();
                return;
            } else {
                ShoppingList shoppingList = new ShoppingList(name.getText().toString());
                dbViewModel.insertShoppingList(shoppingList);
            }


            NavDirections action = CreateListFragmentDirections
                    .actionCreateListFragmentToShoppingListsFragment();
            Navigation.findNavController(view).navigate(action);
        });
    }
}
