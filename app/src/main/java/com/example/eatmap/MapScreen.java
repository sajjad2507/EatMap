package com.example.eatmap;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapScreen extends FragmentActivity implements OnMapReadyCallback {

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set fullscreen flags
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_map_screen);

        // Initialize the FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Fetch the current location
        fetchLocation();
    }

    private void fetchLocation() {
        // Check for location permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request location permission if not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }

        // Fetch the last known location
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    // Save the current location
                    currentLocation = location;

                    // Initialize the SupportMapFragment and set the map callback
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    assert supportMapFragment != null;
                    supportMapFragment.getMapAsync(MapScreen.this::onMapReady);
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to get current location", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Get the Intent that started this activity
        Intent intent = getIntent();

        // Extract the data from the Intent using the keys you used while putting them
        double latitude = intent.getDoubleExtra("latitude", 0);
        double longitude = intent.getDoubleExtra("longitude", 0);

        // Create LatLng objects for restaurant and user locations
        LatLng latLngRes = new LatLng(latitude, longitude);
        LatLng latLngUser = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());

        // Create marker options for restaurant and user locations
        MarkerOptions markerOptionsRes = new MarkerOptions().position(latLngRes).title("Restaurant");
        MarkerOptions markerOptionsUser = new MarkerOptions().position(latLngUser).title("User Location!");

        // Move and animate the camera to the restaurant and user locations
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLngRes));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngRes, 5));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLngUser));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngUser, 5));

        // Add markers for restaurant and user locations to the map
        googleMap.addMarker(markerOptionsRes);
        googleMap.addMarker(markerOptionsUser);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // If location permission is granted, fetch the current location
                fetchLocation();
            }
        }
    }
}