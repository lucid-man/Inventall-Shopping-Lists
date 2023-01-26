package com.inventall.shoppinglists.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.inventall.shoppinglists.R;
import com.inventall.shoppinglists.fragments.ShoppingListsFragmentDirections;
import com.inventall.shoppinglists.roomdatabase.ShoppingList;

import java.util.List;

public class ShoppingListsAdapter extends RecyclerView.Adapter<ShoppingListsAdapter.ViewHolder>{

    private List<ShoppingList> shoppingLists;

    public ShoppingListsAdapter(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_shopping_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.listName.setText(shoppingLists.get(position).getName());

        holder.cardContainer.setOnClickListener(v -> {
            NavDirections action = ShoppingListsFragmentDirections
                    .actionShoppingListsFragmentToItemsFragment(shoppingLists.get(position).getId());
            Navigation.findNavController(v).navigate(action);
        });

        holder.btnMore.setOnClickListener(v -> {
            NavDirections action = ShoppingListsFragmentDirections
                    .actionShoppingListsFragmentToEditListFragment(shoppingLists.get(position).getId());
            Navigation.findNavController(v).navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardContainer;
        private TextView listName;
        private ImageButton btnMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardContainer = itemView.findViewById(R.id.shopping_lists_card);
            listName = itemView.findViewById(R.id.shopping_lists_name);
            btnMore = itemView.findViewById(R.id.shopping_lists_btn_more);

        }
    }
}
