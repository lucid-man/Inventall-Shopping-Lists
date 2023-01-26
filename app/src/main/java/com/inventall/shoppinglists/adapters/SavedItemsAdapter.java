package com.inventall.shoppinglists.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.inventall.shoppinglists.Constants;
import com.inventall.shoppinglists.R;
import com.inventall.shoppinglists.fragments.SavedOrNewItemFragmentDirections;
import com.inventall.shoppinglists.models.DatabaseViewModel;
import com.inventall.shoppinglists.roomdatabase.SavedItem;

import java.util.List;

public class SavedItemsAdapter extends RecyclerView.Adapter<SavedItemsAdapter.ViewHolder> {

    private List<SavedItem> savedItems;
    private int shoppingListId;
    private DatabaseViewModel dbViewModel;

    public SavedItemsAdapter(List<SavedItem> savedItems, int shoppingListId, DatabaseViewModel dbViewModel) {
        this.savedItems = savedItems;
        this.shoppingListId = shoppingListId;
        this.dbViewModel = dbViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_saved_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageCategory.setImageResource(
                Constants.categoriesImages[savedItems.get(position).getCategory()]
                );
        holder.textName.setText(savedItems.get(position).getName());

        holder.card.setOnClickListener(v -> {
            NavDirections action = SavedOrNewItemFragmentDirections
                    .actionSavedOrNewItemFragmentToAddSavedItemFragment(
                            shoppingListId, savedItems.get(position).getId());
            Navigation.findNavController(v).navigate(action);
        });

        holder.card.setOnLongClickListener(v -> {
            dbViewModel.deleteSavedItem(savedItems.get(position));
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return savedItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView card;
        private ImageView imageCategory;
        private TextView textName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.rec_saved_items_card);
            imageCategory = itemView.findViewById(R.id.rec_saved_items_image_category);
            textName = itemView.findViewById(R.id.rec_saved_items_text_name);
        }
    }
}
