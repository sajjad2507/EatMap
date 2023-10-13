package com.example.eatmap.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_RESTAURANT = "restaurant";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE_LINK = "image_link";
    public static final String COLUMN_VIDEO_LINK = "video_link";
    public static final String COLUMN_SPECIAL_DISHES = "special_dishes";
    public static final String COLUMN_LONGITUDE = "longitude"; // Add longitude column
    public static final String COLUMN_LATITUDE = "latitude";   // Add latitude column

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_RESTAURANT + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_DESCRIPTION + " text, "
            + COLUMN_IMAGE_LINK + " text, "
            + COLUMN_VIDEO_LINK + " text, "
            + COLUMN_SPECIAL_DISHES + " text, "
            + COLUMN_LONGITUDE + " real, "   // Add longitude column definition
            + COLUMN_LATITUDE + " real);";   // Add latitude column definition

    // Constructor
    public RestaurantDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // Create the database table
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade the database by dropping the existing table and creating a new one
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        onCreate(db);
    }
}

