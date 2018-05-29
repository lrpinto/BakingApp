package com.example.developer.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.developer.bakingapp.R;
import com.example.developer.bakingapp.data.Recipe;
import com.example.developer.bakingapp.network.RequestInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


// This fragment displays all of the Recipe Cards in one large list
// The list appears as a list of recipe cards
public class RecipeListFragment extends Fragment {

    // The callback is a method named onRecipeSelected(int position) that contains information about
    // which position on the grid of recipes a user has clicked
    // Define a new interface OnRecipeClickListener that triggers a callback in the host activity
    OnRecipeClickListener mCallback;

    GridView gridView;
    RecipeListAdapter mAdapter;
    private ArrayList<Recipe> recipes;

    // Mandatory empty constructor
    public RecipeListFragment() {
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

        final View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        // Get a reference to the GridView in the fragment_recipe_list xml layout file
        gridView = rootView.findViewById(R.id.recipe_cards_grid_view);

        loadRecipes();

        // Set a click listener on the gridview and trigger the callback onRecipeSelected when a Recipe card is clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Recipe recipe = recipes.get(position);

                // Trigger the callback method and pass in the position that was clicked
                mCallback.onRecipeSelected(recipe);
            }
        });

        // Return the root view
        return rootView;
    }

    // OnRecipeClickListener interface, calls a method in the host activity named onRecipeSelected
    public interface OnRecipeClickListener {
        void onRecipeSelected(Recipe position);
    }

    private void loadRecipes() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);

        Call<ArrayList<Recipe>> call = request.getRecipes();
        call.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {

                recipes = response.body();

                // Create the adapter
                // This adapter takes in the context and an ArrayList of ALL the recipes to display
                mAdapter = new RecipeListAdapter(getContext(), recipes);

                // Set the adapter on the GridView
                gridView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }


        });
    }


}