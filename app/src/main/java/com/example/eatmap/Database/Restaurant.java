package com.example.eatmap.Database;

public class Restaurant {
    private long id;
    private String name;
    private String description;
    private String imageLink;
    private String videoLink;
    private String specialDishes;
    private double longitude;
    private double latitude;

// Constructors

    // Empty constructor needed for creating instances without setting properties
    public Restaurant() {
    }

    // Constructor with parameters to initialize all properties
    public Restaurant(String name, String description, String imageLink, String videoLink, String specialDishes, double longitude, double latitude) {
        this.name = name;
        this.description = description;
        this.imageLink = imageLink;
        this.videoLink = videoLink;
        this.specialDishes = specialDishes;
        this.longitude = longitude;
        this.latitude = latitude;
    }

// Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getSpecialDishes() {
        return specialDishes;
    }

    public void setSpecialDishes(String specialDishes) {
        this.specialDishes = specialDishes;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        // Return a string representation of the Restaurant object for debugging or logging purposes
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", videoLink='" + videoLink + '\'' +
                ", specialDishes='" + specialDishes + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
