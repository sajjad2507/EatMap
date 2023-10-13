package com.example.eatmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailScreen extends AppCompatActivity {

    ImageView detailImage;
    TextView restaurantTitle, restaurantDetails, moreAbout, watchVideoTv;
    Button seeLocationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set fullscreen flags
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_detail_screen);

        // Hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        detailImage = findViewById(R.id.detailImage);
        restaurantTitle = findViewById(R.id.restaurantTitle);
        restaurantDetails = findViewById(R.id.restaurantDetails);
        moreAbout = findViewById(R.id.moreAbout);
        watchVideoTv = findViewById(R.id.watchVideoTv);
        seeLocationBtn = findViewById(R.id.seeLocationBtn);

        // Get the Intent that started this activity
        Intent intent = getIntent();

        // Extract the data from the Intent using the keys you used while putting them
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String imageLink = intent.getStringExtra("image");
        String videoLink = intent.getStringExtra("video");
        String specialDishes = intent.getStringExtra("dishes");
        Double latitude = intent.getDoubleExtra("latitude",00000);
        Double longitude = intent.getDoubleExtra("longitude", 00000);

        // Get the resource identifier for the drawable using its name and package
        int imageResource = getResources().getIdentifier(imageLink, "drawable", getPackageName());
        // Set the image resource to the ImageView
        detailImage.setImageResource(imageResource);

        restaurantTitle.setText(name);
        restaurantDetails.setText(description);
        moreAbout.setText(specialDishes);

        watchVideoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailScreen.this, VideoScreen.class);
                intent.putExtra("video", videoLink.trim());
                startActivity(intent);
            }
        });

        seeLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailScreen.this, MapScreen.class);
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", latitude);
                startActivity(intent);
            }
        });

    }
}