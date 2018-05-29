package com.example.developer.bakingapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.developer.bakingapp.R;
import com.example.developer.bakingapp.data.Recipe;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.OnRecipeClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRecipeSelected(Recipe recipe) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Recipe clicked = " + recipe.getName(), Toast.LENGTH_SHORT).show();

        // Log the clicked recipe
        Log.i("Info", recipe.toString());

        // Handle the single-pane phone case by passing information in a Bundle attached to an Intent

        /*
        // Attach the Recipe to an intent
        final Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra("RECIPE", recipe);
        startActivity(intent);
        */

    }

}
