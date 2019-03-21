package com.bookt.bookt_admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    DatabaseReference myRef ;
    TextView array_size;
    ArrayList<Restaurant> restaurantArrayList;
    RecyclerView recyclerView;
    RestaurantAdaptar restaurantAdaptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        InputStream is =
                null;
        FirebaseOptions options =
                null;
        try {
            is= getClass().getResourceAsStream("/assets/bookt-a9889-firebase-adminsdk-rhmv1-5520376680.json");

            options  = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(is))
                    .setDatabaseUrl("https://bookt-a9889.firebaseio.com")
                    .build();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        FirebaseApp.initializeApp(options);








        array_size = findViewById(R.id.array_size);
        restaurantArrayList = new ArrayList<>();


        recyclerView = findViewById(R.id.restaurantRecyclerView);
        restaurantAdaptar = new RestaurantAdaptar(this,restaurantArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(restaurantAdaptar);




                   FirebaseDatabase.getInstance().getReference("Restaurants").addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot snapshot) {
                           array_size.setText(snapshot.getChildrenCount() + "");

                           for(DataSnapshot snapshot1 : snapshot.getChildren()){
                               Restaurant restaurant =snapshot1.getValue(Restaurant.class);
                               restaurant.setFirebaseId(snapshot1.getKey());
                               System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> " + snapshot1.toString());
                               restaurantArrayList.add(restaurant);

                           }
                           restaurantAdaptar.notifyDataSetChanged();
                           System.out.println("byeeeeeeeeeeeeeeeeeeee");

                       }

                       @Override
                       public void onCancelled(DatabaseError error) {
                           System.out.println(error);
                       }
                   });





            }



}
