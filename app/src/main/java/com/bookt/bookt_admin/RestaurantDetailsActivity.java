package com.bookt.bookt_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

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

        database = MainActivity.database;
        myRef = MainActivity.myRef;
        TextView confirm = findViewById(R.id.confirm);




        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = myRef.getDatabase().getReference("Restaurants").child(restaurant.getFirebaseId());
                myRef.setValue(restaurant);
                myRef = myRef.getDatabase().getReference("Country").
                        child("Saudi Arabia").
                        child("cities").
                        child(restaurant.getRestaurant_city()).
                        child("Cuisine").
                        child("Cuisine_names").
                        child(restaurant.getRestaurant_cuisine());
                    Type type = new Type(restaurant.getRestaurant_cuisine());
                    myRef.setValue(type);
                myRef = myRef.getDatabase().getReference("Country").
                        child("Saudi Arabia").
                        child("cities").
                        child(restaurant.getRestaurant_city()).
                        child("Cuisine").
                        child("ids").
                        child(restaurant.getRestaurant_cuisine()).
                        child(restaurant.getFirebaseId());
                myRef.setValue(restaurant);
            }
        });




    }
}
