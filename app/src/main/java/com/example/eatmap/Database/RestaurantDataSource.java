package com.example.eatmap.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.eatmap.Database.Restaurant;
import com.example.eatmap.Database.RestaurantDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDataSource {

    private SQLiteDatabase database;
    private RestaurantDatabaseHelper dbHelper;
    private String[] allColumns = {
            RestaurantDatabaseHelper.COLUMN_ID,
            RestaurantDatabaseHelper.COLUMN_NAME,
            RestaurantDatabaseHelper.COLUMN_DESCRIPTION,
            RestaurantDatabaseHelper.COLUMN_IMAGE_LINK,
            RestaurantDatabaseHelper.COLUMN_VIDEO_LINK,
            RestaurantDatabaseHelper.COLUMN_SPECIAL_DISHES,
            RestaurantDatabaseHelper.COLUMN_LONGITUDE, // Add longitude column
            RestaurantDatabaseHelper.COLUMN_LATITUDE   // Add latitude column
    };

    // Constructor
    public RestaurantDataSource(Context context) {
        dbHelper = new RestaurantDatabaseHelper(context);
    }

    // Open the database for writing
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Close the database
    public void close() {
        dbHelper.close();
    }

    // Insert a new restaurant into the database
    public void createRestaurant(Restaurant restaurant) {
        ContentValues values = new ContentValues();
        values.put(RestaurantDatabaseHelper.COLUMN_NAME, restaurant.getName());
        values.put(RestaurantDatabaseHelper.COLUMN_DESCRIPTION, restaurant.getDescription());
        values.put(RestaurantDatabaseHelper.COLUMN_IMAGE_LINK, restaurant.getImageLink());
        values.put(RestaurantDatabaseHelper.COLUMN_VIDEO_LINK, restaurant.getVideoLink());
        values.put(RestaurantDatabaseHelper.COLUMN_SPECIAL_DISHES, restaurant.getSpecialDishes());
        values.put(RestaurantDatabaseHelper.COLUMN_LONGITUDE, restaurant.getLongitude()); // Set longitude
        values.put(RestaurantDatabaseHelper.COLUMN_LATITUDE, restaurant.getLatitude());   // Set latitude

        long insertId = database.insert(RestaurantDatabaseHelper.TABLE_RESTAURANT, null, values);
        restaurant.setId(insertId);
    }

    // Get all restaurants from the database
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();

        Cursor cursor = database.query(RestaurantDatabaseHelper.TABLE_RESTAURANT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Restaurant restaurant = cursorToRestaurant(cursor);
            restaurants.add(restaurant);
            cursor.moveToNext();
        }

        cursor.close();
        return restaurants;
    }

    // Helper method to convert Cursor to Restaurant object
    private Restaurant cursorToRestaurant(Cursor cursor) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(cursor.getLong(0));
        restaurant.setName(cursor.getString(1));
        restaurant.setDescription(cursor.getString(2));
        restaurant.setImageLink(cursor.getString(3));
        restaurant.setVideoLink(cursor.getString(4));
        restaurant.setSpecialDishes(cursor.getString(5));
        restaurant.setLongitude(cursor.getDouble(6)); // Get longitude
        restaurant.setLatitude(cursor.getDouble(7));  // Get latitude
        return restaurant;
    }
}
