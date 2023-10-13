package com.example.eatmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class TermsScreen extends AppCompatActivity {

    Button termsBtn;
    private static final String PREFS_NAME = "CheckInsertion"; // Name for SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for the activity
        setContentView(R.layout.activity_terms_screen);

        // Initialize the "termsBtn" button by finding its view from the layout
        termsBtn = findViewById(R.id.termsBtn);

        // Set a click listener on the "termsBtn" button
        termsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the button is clicked, set the "termsCheck" flag to true in SharedPreferences
                SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("termsCheck", true);
                editor.apply();

                // Start the MainActivity
                Intent intent = new Intent(TermsScreen.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish the current activity so that the user can't go back to the TermsScreen
            }
        });
    }
}