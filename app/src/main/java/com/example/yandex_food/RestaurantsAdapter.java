package com.example.yandex_food;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder> {
    private ArrayList<Restaurants> restaurants;
    private ImageView cost1;
    private ImageView cost2;
    private ImageView cost3;
    public RestaurantsAdapter(ArrayList<Restaurants> restaurants){
        this.restaurants = restaurants;
    }

    class RestaurantsViewHolder extends RecyclerView.ViewHolder{
        ImageView id_img;
        TextView time;
        TextView stars;
        TextView restaurant;
        RestaurantsViewHolder(View view){
            super(view);
            id_img = view.findViewById(R.id.img);
            time = view.findViewById(R.id.time);
            cost1 = view.findViewById(R.id.cost1);
            cost2 = view.findViewById(R.id.cost2);
            cost3 = view.findViewById(R.id.cost3);
            stars = view.findViewById(R.id.star);
            restaurant = view.findViewById(R.id.restaurant);
        }
    }

    @NonNull
    @Override
    public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.maket_card_recyclerview,parent,false);
        return new RestaurantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsViewHolder holder, int position) {
        switch (restaurants.get(position).getId_img()){
            case 1:
                holder.id_img.setImageResource(R.drawable.img1);
                break;
            case 2:
                holder.id_img.setImageResource(R.drawable.img2);
                break;
            case 3:
                holder.id_img.setImageResource(R.drawable.img3);
                break;
            case 4:
                holder.id_img.setImageResource(R.drawable.img4);
                break;
            case 5:
                holder.id_img.setImageResource(R.drawable.img5);
                break;
            case 6:
                holder.id_img.setImageResource(R.drawable.img6);
                break;
            case 7:
                holder.id_img.setImageResource(R.drawable.img7);
                break;

        }
        holder.time.setText(restaurants.get(position).getTime());
         switch (restaurants.get(position).getCost()){
            case 1:
                cost1.setBackgroundResource(R.drawable.ruble_yes);
                cost2.setBackgroundResource(R.drawable.ruble_no);
                cost3.setBackgroundResource(R.drawable.ruble_no);
                break;
            case 2:
                cost1.setBackgroundResource(R.drawable.ruble_yes);
                cost2.setBackgroundResource(R.drawable.ruble_yes);
                cost3.setBackgroundResource(R.drawable.ruble_no);
                break ;
            case 3:
                cost1.setBackgroundResource(R.drawable.ruble_yes);
                cost2.setBackgroundResource(R.drawable.ruble_yes);
                cost3.setBackgroundResource(R.drawable.ruble_yes);
                break;
        }
        holder.stars.setText(restaurants.get(position).getStars());
        holder.restaurant.setText(restaurants.get(position).getRestaurant());

    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
