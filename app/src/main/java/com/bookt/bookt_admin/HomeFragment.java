package com.bookt.bookt_admin;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    FirebaseDatabase database ;
    DatabaseReference myRef ;
    TextView array_size;
    ArrayList<Restaurant> restaurantArrayList;
    RecyclerView recyclerView;
    RestaurantAdaptar restaurantAdaptar;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        database = MainActivity.database;
        myRef = MainActivity.myRef;
        array_size = view.findViewById(R.id.array_size);
        restaurantArrayList = new ArrayList<>();


        recyclerView = view.findViewById(R.id.restaurantRecyclerView);
        restaurantAdaptar = new RestaurantAdaptar(getContext(),restaurantArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(restaurantAdaptar);

        myRef.getDatabase().getReference("QueueList");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                array_size.setText(dataSnapshot.getChildrenCount()+"");
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    Restaurant restaurant = dataSnapshot1.getValue(Restaurant.class);
                    restaurant.setFirebaseId(dataSnapshot1.getKey());
                    restaurantArrayList.add(restaurant);
                    restaurantAdaptar.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return  view;
    }

}
