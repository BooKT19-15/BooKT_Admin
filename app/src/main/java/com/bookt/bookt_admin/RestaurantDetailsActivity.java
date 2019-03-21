package com.bookt.bookt_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RestaurantDetailsActivity extends AppCompatActivity {

    FirebaseDatabase database ;
    DatabaseReference myRef ;
    Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        Intent intent =getIntent();


        restaurant = intent.getParcelableExtra("Restaurant");


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("QueueList");
        TextView confirm = findViewById(R.id.confirm);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = myRef.getDatabase().getReference("Restaurants").child(restaurant.getFirebaseId());
                myRef.setValueAsync(restaurant);
                myRef = myRef.getDatabase().getReference("Country").
                        child("Saudi Arabia").
                        child("cities").
                        child(restaurant.getRestaurant_city()).
                        child("Cuisine").
                        child("Cuisine_names").
                        child(restaurant.getRestaurant_cuisine());
                    Type type = new Type(restaurant.getRestaurant_cuisine());
                    myRef.setValueAsync(type);
                myRef = myRef.getDatabase().getReference("Country").
                        child("Saudi Arabia").
                        child("cities").
                        child(restaurant.getRestaurant_city()).
                        child("Cuisine").
                        child("ids").
                        child(restaurant.getRestaurant_cuisine()).
                        child(restaurant.getFirebaseId());
                myRef.setValueAsync(restaurant);
            }
        });




    }
}
