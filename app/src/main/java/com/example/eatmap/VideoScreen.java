package com.example.eatmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoScreen extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set fullscreen flags to hide the status bar and action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set the layout for the activity
        setContentView(R.layout.activity_video_screen);

        // Hide the action bar (if available)
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Find the VideoView by its ID from the layout
        videoView = findViewById(R.id.videoView);

        // Get the Intent that started this activity
        Intent intent = getIntent();

        // Extract the data from the Intent using the keys you used while putting them
        String video = intent.getStringExtra("video");

        // Get the resource identifier for the raw resource using its name and package
        int videoResource = getResources().getIdentifier(video, "raw", getPackageName());

        // If the resource identifier is 0, it means the video resource was not found
        if (videoResource != 0) {
            // Set the video file URI to the VideoView
            Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + videoResource);
            videoView.setVideoURI(videoUri);

            // Set up MediaController to provide playback controls
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

            // Start playing the video
            videoView.start();
        } else {
            // Handle the case when the video resource is not found (e.g., show an error message)
            // You may consider displaying a toast message or an error dialog to inform the user
            // about the missing video resource.
        }
    }
}