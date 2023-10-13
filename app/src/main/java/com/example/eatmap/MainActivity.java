package com.example.eatmap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eatmap.Adapter.RestaurantAdapter;
import com.example.eatmap.Database.Restaurant;
import com.example.eatmap.Database.RestaurantDataSource;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RestaurantDataSource dataSource;
    private RecyclerView restaurantRecyclerView;
    private RestaurantAdapter restaurantAdapter;
    ImageView policyIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set fullscreen flags
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // Hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize the RestaurantDataSource
        dataSource = new RestaurantDataSource(this);
        dataSource.open();

        // Get references to the views in the layout
        restaurantRecyclerView = findViewById(R.id.restaurantsRcv);
        policyIcon = findViewById(R.id.policyIcon);

        // Initialize the RestaurantAdapter
        restaurantAdapter = new RestaurantAdapter(this);

        // Retrieve all restaurants from the database
        List<Restaurant> restaurants = dataSource.getAllRestaurants();

        // Pass the list of restaurants to the adapter
        restaurantAdapter.setRestaurants(restaurants);

        // Set the adapter on the RecyclerView
        restaurantRecyclerView.setAdapter(restaurantAdapter);

        // Set the GridLayoutManager with two items per row
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        restaurantRecyclerView.setLayoutManager(gridLayoutManager);

        // Set a click listener on the policyIcon ImageView
        policyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the TermsScreen activity when the policyIcon is clicked
                Intent intent = new Intent(MainActivity.this, TermsScreen.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database when the activity is destroyed to release resources
        dataSource.close();
    }
}