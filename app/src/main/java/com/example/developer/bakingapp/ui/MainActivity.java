package com.example.developer.bakingapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.developer.bakingapp.R;
import com.example.developer.bakingapp.ui.RecipeDetailActivity;
import com.example.developer.bakingapp.ui.MasterListFragment;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnRecipeClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRecipeSelected(int position) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        /*
        // Handle the single-pane phone case by passing information in a Bundle attached to an Intent

        // Put the recipe index information in a Bundle and attach it to an Intent that will launch an RecipeDetailActivity
        Bundle b = new Bundle();
        b.putInt("recipeIndex", position);

        // Attach the Bundle to an intent
        final Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtras(b);
        startActivity(intent);
        */

    }
}
