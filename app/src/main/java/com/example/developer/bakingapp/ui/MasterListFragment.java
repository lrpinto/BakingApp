package com.example.developer.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.developer.bakingapp.R;
import com.example.developer.bakingapp.data.Recipes;


// This fragment displays all of the Recipe Cars in one large list
// The list appears as a list of recipe cards
public class MasterListFragment extends Fragment {

    // The callback is a method named onRecipeSelected(int position) that contains information about
    // which position on the grid of recipes a user has clicked
    // Define a new interface OnRecipeClickListener that triggers a callback in the host activity
    OnRecipeClickListener mCallback;

    // Mandatory empty constructor
    public MasterListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (OnRecipeClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnRecipeClickListener");
        }
    }

    // Inflates the GridView of all Recipe Cards
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        // Get a reference to the GridView in the fragment_master_list xml layout file
        GridView gridView = rootView.findViewById(R.id.recipe_cards_grid_view);

        // Create the adapter
        // This adapter takes in the context and an ArrayList of ALL the recipes to display
        final MasterListAdapter mAdapter = new MasterListAdapter(getContext(), Recipes.getAll());

        // Set the adapter on the GridView
        gridView.setAdapter(mAdapter);

        // Set a click listener on the gridview and trigger the callback onRecipeSelected when a Recipe card is clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Trigger the callback method and pass in the position that was clicked
                mCallback.onRecipeSelected(position);
            }
        });

        // Return the root view
        return rootView;
    }

    // OnRecipeClickListener interface, calls a method in the host activity named onRecipeSelected
    public interface OnRecipeClickListener {
        void onRecipeSelected(int position);
    }

}