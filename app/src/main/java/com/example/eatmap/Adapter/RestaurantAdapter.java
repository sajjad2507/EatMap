package com.example.eatmap.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatmap.Database.Restaurant;
import com.example.eatmap.DetailScreen;
import com.example.eatmap.MainActivity;
import com.example.eatmap.R;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private static List<Restaurant> restaurants;
    private static Context context; // Add a field to hold the context

    // Constructor to receive the context
    public RestaurantAdapter(Context context) {
        this.context = context;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for the item view (item_card.xml)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        // Get the restaurant at the current position in the list
        Restaurant restaurant = restaurants.get(position);

        // Set the restaurant details to the appropriate views in the ViewHolder
        holder.nameTextView.setText(restaurant.getName());
        holder.itemDescription.setText(restaurant.getDescription());

        // Get the resource identifier for the drawable using its name and package
        int imageResource = context.getResources().getIdentifier(restaurant.getImageLink(), "drawable", context.getPackageName());

        // Set the image resource to the ImageView
        holder.imageView.setImageResource(imageResource);

        // Set other details as needed (e.g., image, video, special dishes)
        // If there are other views for restaurant details in the ViewHolder, set them here.
    }

    @Override
    public int getItemCount() {
        // Return the number of restaurants in the list, or 0 if the list is null
        return restaurants != null ? restaurants.size() : 0;
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, itemDescription;
        ImageView imageView;
        // Other views for restaurant details

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views in the ViewHolder using their IDs from the item_card.xml layout
            nameTextView = itemView.findViewById(R.id.itemHeader);
            imageView = itemView.findViewById(R.id.cardImage);
            itemDescription = itemView.findViewById(R.id.itemDescription);
            // Initialize other views as needed

            // Set click listener on the item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Get the restaurant at the clicked position
                        Restaurant restaurant = restaurants.get(position);

                        // Create an Intent to open the DetailScreen activity and pass restaurant details
                        Intent intent = new Intent(context, DetailScreen.class);
                        intent.putExtra("name", restaurant.getName());
                        intent.putExtra("description", restaurant.getDescription());
                        intent.putExtra("image", restaurant.getImageLink());
                        intent.putExtra("video", restaurant.getVideoLink());
                        intent.putExtra("dishes", restaurant.getSpecialDishes());
                        intent.putExtra("latitude", restaurant.getLatitude());
                        intent.putExtra("longitude", restaurant.getLongitude());

                        // Start the DetailScreen activity with the provided Intent
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
