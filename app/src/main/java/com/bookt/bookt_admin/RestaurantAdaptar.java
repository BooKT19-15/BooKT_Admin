package com.bookt.bookt_admin;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RestaurantAdaptar extends RecyclerView.Adapter<RestaurantAdaptar.MyViewHolder> {
    Context context;
    ArrayList<Restaurant> restaurantArrayList;

    public RestaurantAdaptar(Context context, ArrayList<Restaurant> restaurantArrayList) {
        this.context = context;
        this.restaurantArrayList = restaurantArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.info_cardview,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        myViewHolder.restaurantName.setText(restaurantArrayList.get(i).getRestaurant_name());
        myViewHolder.city.setText(restaurantArrayList.get(i).getRestaurant_city());
        myViewHolder.cName.setText(new StringBuilder().append(restaurantArrayList.get(i).getPerson().getFirst_name()).append(" ").append(restaurantArrayList.get(i).getPerson().getLast_name()).toString());
        myViewHolder.cEmail.setText(restaurantArrayList.get(i).getPerson().getEmail());
        myViewHolder.cPhone.setText(restaurantArrayList.get(i).getPerson().getMobile_number());

        myViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,RestaurantDetailsActivity.class);
                intent.putExtra("Restaurant",restaurantArrayList.get(i));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return restaurantArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout constraintLayout;
        TextView restaurantName;
        TextView city;
        TextView cName;
        TextView cEmail;
        TextView cPhone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cardview);
            restaurantName = itemView.findViewById(R.id.restaurant);
            city = itemView.findViewById(R.id.city);
            cName = itemView.findViewById(R.id.c_name);
            cEmail = itemView.findViewById(R.id.c_email);
            cPhone = itemView.findViewById(R.id.c_phone);

        }
    }
}
