package com.example.developer.bakingapp.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


// Custom adapter class that displays a list of recipes in a GridView
public class MasterListAdapter extends BaseAdapter {

    // Keeps track of the context and list of recipes to display
    private Context mContext;
    private List<String> mRecipes;

    /**
     * Constructor method
     *
     * @param recipes The list of recipes to display
     */
    public MasterListAdapter(Context context, List<String> recipes) {
        mContext = context;
        mRecipes = recipes;
    }

    /**
     * Returns the number of items the adapter will display
     */
    @Override
    public int getCount() {
        return mRecipes.size();
    }

    @Override
    public Object getItem(int i) {
        return mRecipes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Creates a new TextView for each recipe referenced by the adapter
     */
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            // If the view is not recycled, this creates a new TextView to hold the Recipe text
            textView = new TextView(mContext);
            // Define the layout parameters
            textView.setPadding(8, 8, 8, 8);
        } else {
            textView = (TextView) convertView;
        }

        // Set the text resource and return the newly created TextView
        textView.setText(mRecipes.get(position));
        return textView;
    }

}