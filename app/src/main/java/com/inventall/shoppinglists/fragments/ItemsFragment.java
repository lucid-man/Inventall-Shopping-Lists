package com.inventall.shoppinglists.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inventall.shoppinglists.R;
import com.inventall.shoppinglists.adapters.ItemsAdapter;
import com.inventall.shoppinglists.models.DatabaseViewModel;
import com.inventall.shoppinglists.roomdatabase.Item;
import com.inventall.shoppinglists.roomdatabase.ShoppingList;

import java.util.List;

public class ItemsFragment extends Fragment {

    int shoppingListId;

    DatabaseViewModel dbViewModel;
    RecyclerView recyclerView;
    CardView cardNoItemsMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shoppingListId = ItemsFragmentArgs.fromBundle(getArguments()).getShoppingListId();


        recyclerView = view.findViewById(R.id.items_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        dbViewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);
        dbViewModel.getAllItemsFromList(shoppingListId)
                .observe(getViewLifecycleOwner(), this::setRecyclerViewAdapter);



        TextView listNameText = view.findViewById(R.id.items_text_list_name);
        FloatingActionButton fabAddItem = view.findViewById(R.id.items_fab_add);
        cardNoItemsMessage = view.findViewById(R.id.items_card_no_items);

        dbViewModel.getShoppingList(shoppingListId).observe(getViewLifecycleOwner(), shoppingList -> {
            listNameText.setText(shoppingList.getName());
        });



        fabAddItem.setOnClickListener(v -> {
            NavDirections action = ItemsFragmentDirections
                    .actionItemsFragmentToSavedOrNewItemFragment(shoppingListId);
            Navigation.findNavController(view).navigate(action);
        });

    }




    private void setRecyclerViewAdapter(List<Item> items) {
        if (items.isEmpty()) cardNoItemsMessage.setVisibility(View.VISIBLE);
        else cardNoItemsMessage.setVisibility((View.GONE));
        recyclerView.setAdapter(new ItemsAdapter(items, dbViewModel));
    }
}
