package com.example.eatmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;

import com.example.eatmap.Database.Restaurant;
import com.example.eatmap.Database.RestaurantDataSource;

public class SplashScreen extends AppCompatActivity {

    private static final String PREFS_NAME = "CheckInsertion"; // Name for SharedPreferences
    private RestaurantDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set fullscreen flags
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        // Hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize the RestaurantDataSource
        dataSource = new RestaurantDataSource(this);

        // Get the SharedPreferences for checking data insertion and terms check
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Boolean keyDataAdded = preferences.getBoolean("keyDataAdded", false); // Default value is false if the flag is not found
        Boolean termsCheck = preferences.getBoolean("termsCheck", false); // Default value is false if the flag is not found

        // Delayed intent to start the next activity
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                if (keyDataAdded && termsCheck) {
                    // If both data insertion and terms check are true, start the MainActivity
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (keyDataAdded) {
                        // If data insertion is true but terms check is false, start the TermsScreen
                        Intent intent = new Intent(SplashScreen.this, TermsScreen.class);
                        startActivity(intent);
                        finish();
                    } else if (termsCheck) {
                        // If data insertion is false but terms check is true, add data to the database
                        addData();
                    } else {
                        // If both data insertion and terms check are false, add data to the database
                        addData();
                        // Then start the TermsScreen
                        Intent intent = new Intent(SplashScreen.this, TermsScreen.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        }, 3000); // Delay for 3 seconds before proceeding to the next activity
    }

    // Method to add data to the database
    private void addData() {
        dataSource.open();

        // Create Restaurant 1
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Sweet Cafe");
        restaurant1.setDescription("Discover a sweet haven with our delectable desserts and irresistible treats. Indulge in a delightful experience that will satisfy your cravings and leave you wanting more.");
        restaurant1.setImageLink("resone");
        restaurant1.setVideoLink("v1");
        restaurant1.setSpecialDishes("Decadent Chocolate Lava Cake: Indulge in a rich, warm chocolate cake with a molten center that oozes pure delight.\n" +
                "Creamy New York Cheesecake: Experience the velvety smoothness of our classic cheesecake with a buttery graham cracker crust and luscious toppings.\n" +
                "Fluffy Belgian Waffles: Delight in light and fluffy waffles served with fresh berries, whipped cream, and warm maple syrup.\n" +
                "Delicate Macarons: Experience the delightful crunch and chewy interior of our artisanal French macarons in a variety of flavors.\n" +
                "Heavenly Red Velvet Cupcakes: Indulge in moist red velvet cupcakes topped with luscious cream cheese frosting, a perfect treat for any occasion.");
        restaurant1.setLatitude(3.1492603276942868);
        restaurant1.setLongitude(101.70610749863964);

        // Save Restaurant 1 to the database
        dataSource.createRestaurant(restaurant1);

        // Create Restaurant 2
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Spice Bazaar");
        restaurant2.setDescription("Embark on a culinary journey of exotic flavors and aromatic spices. Our diverse menu features tantalizing dishes that will awaken your taste buds and transport you to distant lands.");
        restaurant2.setImageLink("restwo");
        restaurant2.setVideoLink("v2");
        restaurant2.setSpecialDishes("Fragrant Biryani: Savor the aroma and taste of our fragrant basmati rice cooked with succulent pieces of meat and an array of spices.\n" +
                "Tandoori Delights: Experience the authentic smoky flavors of our tandoor-cooked kebabs and naan bread.\n" +
                "Rich Butter Chicken: Indulge in our creamy tomato-based curry with tender pieces of chicken, a favorite among curry enthusiasts.\n" +
                "Flavorful Vegetarian Thali: Delight in a grand platter of assorted vegetarian dishes that showcase the best of Indian cuisine.\n" +
                "Irresistible Gulab Jamun: End your meal on a sweet note with our soft and syrupy milk-based dumplings.");
        restaurant2.setLatitude(3.148746121172033);
        restaurant2.setLongitude(101.71039903271898);

        // Save Restaurant 2 to the database
        dataSource.createRestaurant(restaurant2);


        // Create Restaurant 3
        Restaurant restaurant3 = new Restaurant();
        restaurant3.setName("Ocean's Catch");
        restaurant3.setDescription("Dive into a seafood paradise where the ocean's bounty awaits. Our restaurant offers a variety of fresh, succulent seafood dishes that will leave you craving for more.");
        restaurant3.setImageLink("resthree");
        restaurant3.setVideoLink("v3");
        restaurant3.setSpecialDishes("Grilled Lemon Herb Salmon: Enjoy our tender and flaky salmon fillet seasoned with zesty lemon and aromatic herbs.\n" +
                "Lobster Linguine: Indulge in a decadent pasta dish featuring succulent chunks of lobster tossed in a rich tomato sauce.\n" +
                "Seared Scallops with Risotto: Experience the perfect marriage of delicate scallops and creamy, flavorful risotto.\n" +
                "Classic Fish and Chips: Relish our crispy, golden-fried fish fillets served with a side of perfectly seasoned fries.\n" +
                "Tropical Fruit Sorbet: Refresh your palate with a delightful sorbet made from a medley of tropical fruits.");
        restaurant3.setLatitude(3.153159718888312);
        restaurant3.setLongitude(101.71430432873116);

        // Save Restaurant 3 to the database
        dataSource.createRestaurant(restaurant3);

        // Create Restaurant 4
        Restaurant restaurant4 = new Restaurant();
        restaurant4.setName("Sizzling Steak");
        restaurant4.setDescription("Treat yourself to a sizzling experience of premium cuts and perfectly cooked steaks. Our steakhouse offers a mouthwatering selection of grilled meats that will satisfy any carnivore.");
        restaurant4.setImageLink("resfour");
        restaurant4.setVideoLink("v4");
        restaurant4.setSpecialDishes("Juicy Ribeye Steak: Indulge in a succulent and flavorful ribeye steak grilled to perfection.\n" +
                "Tender Filet Mignon: Experience the exquisite tenderness and buttery flavor of our filet mignon, a true delicacy.\n" +
                "Hearty Tomahawk Steak: Savor the bone-in tomahawk steak, a majestic cut that promises a delightful dining experience.\n" +
                "Grilled Vegetable Medley: Accompany your steak with a delightful assortment of grilled vegetables bursting with flavor.\n" +
                "Decadent Chocolate Fondue: End your meal with a luscious chocolate fondue, perfect for dipping fruits and marshmallows.");
        restaurant4.setLatitude(3.1554307861447644);
        restaurant4.setLongitude(101.70928323385834);

        // Save Restaurant 4 to the database
        dataSource.createRestaurant(restaurant4);

        // Create Restaurant 5
        Restaurant restaurant5 = new Restaurant();
        restaurant5.setName("Green Garden");
        restaurant5.setDescription("Immerse yourself in the world of fresh and wholesome plant-based dishes. Our restaurant offers an array of delicious vegetarian and vegan options that celebrate the beauty of nature.");
        restaurant5.setImageLink("resfive");
        restaurant5.setVideoLink("v5");
        restaurant5.setSpecialDishes("Nutty Quinoa Salad: Savor the nutty goodness of quinoa combined with fresh vegetables and a zesty dressing.\n" +
                "Stuffed Portobello Mushrooms: Experience the rich and savory flavor of our stuffed portobello mushrooms with a delicious filling.\n" +
                "Vegan Lentil Shepherd's Pie: Indulge in a comforting vegan version of the classic shepherd's pie made with lentils and vegetables.\n" +
                "Veggie Wrap with Hummus: Delight in a hearty wrap filled with colorful veggies and creamy hummus.\n" +
                "Fruity Smoothie Bowl: Treat yourself to a refreshing and nutritious smoothie bowl topped with an assortment of fresh fruits.");
        restaurant5.setLatitude(3.155602187245764);
        restaurant5.setLongitude(101.71820962474335);

        // Save Restaurant 5 to the database
        dataSource.createRestaurant(restaurant5);

        // Create Restaurant 6
        Restaurant restaurant6 = new Restaurant();
        restaurant6.setName("Fiesta Mexicana");
        restaurant6.setDescription("Embark on a journey to Mexico with our vibrant and flavorful Mexican cuisine. Our restaurant offers a fiesta of tastes that will transport you to the heart of Mexico.");
        restaurant6.setImageLink("ressix");
        restaurant6.setVideoLink("v6");
        restaurant6.setSpecialDishes("Authentic Chicken Enchiladas: Indulge in our flavorful chicken enchiladas topped with a rich tomato sauce and melted cheese.\n" +
                "Spicy Beef Tacos: Savor the fiery goodness of our beef tacos topped with fresh salsa and a kick of jalapeno.\n" +
                "Cheesy Quesadillas: Experience the gooey deliciousness of our cheesy quesadillas filled with your choice");
        restaurant6.setLatitude(3.158987353196433);
        restaurant6.setLongitude(101.71361768327847);

        // Save Restaurant 6 to the database
        dataSource.createRestaurant(restaurant6);


        // Create Restaurant 7
        Restaurant restaurant7 = new Restaurant();
        restaurant7.setName("Sushi Haven");
        restaurant7.setDescription("Immerse yourself in the art of Japanese cuisine at Sushi Haven. Our restaurant offers an array of fresh and artfully crafted sushi rolls and traditional Japanese dishes.");
        restaurant7.setImageLink("resseven");
        restaurant7.setVideoLink("v7");
        restaurant7.setSpecialDishes("Rainbow Roll: Indulge in a colorful and flavorful roll featuring a combination of fresh sashimi and avocado on a bed of sushi rice.\n" +
                "Tempura Bento Box: Experience the delicate crunch of tempura paired with a variety of delectable sides in a traditional bento box.\n" +
                "Spicy Tuna Roll: Savor the tantalizing blend of spicy tuna and cucumber, wrapped in a nori seaweed and sushi rice.\n" +
                "Unagi Nigiri: Delight in a slice of grilled eel served over a bed of sushi rice, drizzled with a sweet and savory eel sauce.\n" +
                "Mochi Ice Cream: End your meal with a sweet treat of mochi, a Japanese rice cake filled with ice cream in various flavors.");
        restaurant7.setLatitude(3.1555164866987946);
        restaurant7.setLongitude(101.71842420144733);

        // Save Restaurant 7 to the database
        dataSource.createRestaurant(restaurant7);

        // Create Restaurant 8
        Restaurant restaurant8 = new Restaurant();
        restaurant8.setName("Mediterranean");
        restaurant8.setDescription("Take a journey to the Mediterranean coast with our restaurant's delectable offerings of Mediterranean cuisine. Indulge in a variety of dishes that showcase the flavors of the region.");
        restaurant8.setImageLink("reseight");
        restaurant8.setVideoLink("v8");
        restaurant8.setSpecialDishes("Greek Moussaka: Savor the layers of eggplant, minced meat, and creamy béchamel sauce, creating a comforting and flavorful casserole.\n" +
                "Falafel Pita Wrap: Experience the authentic taste of falafel wrapped in a soft pita bread with fresh vegetables and tahini sauce.\n" +
                "Grilled Lamb Chops: Delight in tender and juicy lamb chops seasoned with aromatic Mediterranean herbs and spices.\n" +
                "Tabbouleh Salad: Enjoy the refreshing combination of bulgur wheat, parsley, tomatoes, and mint, dressed in a tangy lemon vinaigrette.\n" +
                "Baklava: End your Mediterranean feast with the indulgence of baklava, a sweet pastry filled with chopped nuts and drizzled with honey.");
        restaurant8.setLatitude(3.142076057767179);
        restaurant8.setLongitude(101.69778254207716);

        // Save Restaurant 8 to the database
        dataSource.createRestaurant(restaurant8);

        // Save the data insertion flag to SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("keyDataAdded", true);
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the RestaurantDataSource when the activity is destroyed
        dataSource.close();
    }
}