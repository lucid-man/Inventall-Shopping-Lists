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
import com.inventall.shoppinglists.adapters.ShoppingListsAdapter;
import com.inventall.shoppinglists.models.DatabaseViewModel;
import com.inventall.shoppinglists.roomdatabase.ShoppingList;

import java.util.List;

public class ShoppingListsFragment extends Fragment {

    private DatabaseViewModel dbViewModel;

    private RecyclerView recyclerView;
    private CardView noListsMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopping_lists, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        dbViewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);

        dbViewModel.getAllShoppingLists().observe(getViewLifecycleOwner(), this::setRecyclerViewAdapter);

        noListsMessage = view.findViewById(R.id.shopping_lists_card_no_lists);
        recyclerView = view.findViewById(R.id.recycler_shopping_lists);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        FloatingActionButton fabAdd = view.findViewById(R.id.shopping_lists_fab_add);

        fabAdd.setOnClickListener(v -> {
            NavDirections action = ShoppingListsFragmentDirections
                    .actionShoppingListsFragmentToCreateListFragment();
            Navigation.findNavController(view).navigate(action);
        });
    }

    private void setRecyclerViewAdapter(List<ShoppingList> shoppingLists){
        if (shoppingLists.isEmpty()) noListsMessage.setVisibility(View.VISIBLE);
        else noListsMessage.setVisibility(View.GONE);
        recyclerView.setAdapter(new ShoppingListsAdapter(shoppingLists));
    }
}
