package com.inventall.shoppinglists.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.inventall.shoppinglists.Constants;
import com.inventall.shoppinglists.R;
import com.inventall.shoppinglists.models.DatabaseViewModel;
import com.inventall.shoppinglists.roomdatabase.Item;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private List<Item> items;
    private DatabaseViewModel dbViewModel;

    public ItemsAdapter(List<Item> items, DatabaseViewModel dbViewModel) {
        this.items = items;
        this.dbViewModel = dbViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_items, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.categoryImage.setImageResource(Constants.categoriesImages[item.getCategory()]);
        holder.categoryText.setText(holder.categories[item.getCategory()]);
        if (item.getTag() != null) holder.tagText.setText(item.getTag());
        holder.amountText.setText(String.valueOf(item.getAmount()));
        holder.nameText.setText(item.getName());



        holder.cardContainer.setOnLongClickListener(v -> {
            dbViewModel.deleteItem(item);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardContainer;
        private ImageView categoryImage;
        private TextView categoryText, tagText, amountText, nameText;
        private String[] categories;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categories = itemView.getResources().getStringArray(R.array.categories_array);
            cardContainer = itemView.findViewById(R.id.rec_items_card);
            categoryImage = itemView.findViewById(R.id.rec_items_image_category);
            categoryText = itemView.findViewById(R.id.rec_items_text_category);
            tagText = itemView.findViewById(R.id.rec_items_text_tag);
            amountText = itemView.findViewById(R.id.rec_items_text_amount);
            nameText = itemView.findViewById(R.id.rec_items_text_name);
        }
    }
}
