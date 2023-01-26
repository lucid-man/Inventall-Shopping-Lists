package com.inventall.shoppinglists.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inventall.shoppinglists.Constants;
import com.inventall.shoppinglists.R;

public class CategoryAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private String[] categories;

    public CategoryAdapter(LayoutInflater layoutInflater, String[] categories) {
        this.inflater = layoutInflater;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.spinner_category, null);
        }

        ImageView imageView = view.findViewById(R.id.spinner_category_image);
        TextView textView = view.findViewById(R.id.spinner_category_text);

        imageView.setImageResource(Constants.categoriesImages[position]);
        textView.setText(categories[position]);

        return view;

    }
}
