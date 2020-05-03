package com.example.yandex_food;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder> {
    private ArrayList<Restaurants> restaurants;
    private ImageView cost1;
    private ImageView cost2;
    private ImageView cost3;
    private boolean light;
    public RestaurantsAdapter(ArrayList<Restaurants> restaurants){
        this.restaurants = restaurants;
    }
    class RestaurantsViewHolder extends RecyclerView.ViewHolder  {
        ImageView id_img,poleIMG,walletIMG,starsIMG;
        TextView restaurant,stars,time,tag1,tag2,min;
        LinearLayout lin_time;
        RestaurantsViewHolder(View view){
            super(view);
            id_img = view.findViewById(R.id.img);
            time = view.findViewById(R.id.time);
            cost1 = view.findViewById(R.id.cost1);
            cost2 = view.findViewById(R.id.cost2);
            cost3 = view.findViewById(R.id.cost3);
            tag1 = view.findViewById(R.id.tag1);
            tag2 = view.findViewById(R.id.tag2);
            stars = view.findViewById(R.id.star);
            restaurant = view.findViewById(R.id.restaurant);
            poleIMG = view.findViewById(R.id.line);
            walletIMG = view.findViewById(R.id.imageView4);
            starsIMG = view.findViewById(R.id.starsIMG);
            min = view.findViewById(R.id.min);
            lin_time = view.findViewById(R.id.lin_time);
        }
    }
    @NonNull
    @Override
    public RestaurantsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.maket_card_recyclerview,parent,false);
        final RestaurantsViewHolder res = new RestaurantsViewHolder(view);  //По сути это макет RecyclerView
        view.setOnClickListener(new View.OnClickListener() {                //Запускаем слушатель кликов на макет RecyclerView
            @Override
            public void onClick(View v) {
                Context context = v.getContext();                              //Берём контекст кликнутой view
                Intent intent = new Intent(context, MenuActivity.class);       //Подготовка Intent
                intent.putExtra("name",res.restaurant.getText());       //Подготовка к передачае данных в другое активити через intent
                context.startActivity(intent);                                 //Запуск активити через контекст view с данными
            }
        });
        return res;
    }
    @Override
    public void onBindViewHolder(@NonNull final RestaurantsViewHolder holder, int position) {
        holder.tag1.setVisibility(View.VISIBLE);
        holder.tag2.setVisibility(View.VISIBLE);
        light = restaurants.get(position).getLight();
        if(light){                              //Если тема светлая
            holder.restaurant.setTextColor(Color.argb(255,0,0,0));
            holder.time.setTextColor(Color.argb(255,119,119,119));
            holder.tag1.setTextColor(Color.argb(255,179,179,179));
            holder.tag2.setTextColor(Color.argb(255,179,179,179));
            holder.stars.setTextColor(Color.argb(255,0,0,0));
            holder.walletIMG.setBackgroundResource(R.drawable.wallet);
            holder.starsIMG.setBackgroundResource(R.drawable.star);
            holder.poleIMG.setBackgroundResource(R.color.white_2);
            holder.lin_time.setBackgroundResource(R.drawable.maket_time);
            holder.min.setTextColor(Color.argb(255,0,0,0));
            switch (restaurants.get(position).getCost()) {
                case 1:
                    cost1.setBackgroundResource(R.drawable.ruble_yes);
                    cost2.setBackgroundResource(R.drawable.ruble_no);
                    cost3.setBackgroundResource(R.drawable.ruble_no);
                    break;
                case 2:
                    cost1.setBackgroundResource(R.drawable.ruble_yes);
                    cost2.setBackgroundResource(R.drawable.ruble_yes);
                    cost3.setBackgroundResource(R.drawable.ruble_no);
                    break;
                case 3:
                    cost1.setBackgroundResource(R.drawable.ruble_yes);
                    cost2.setBackgroundResource(R.drawable.ruble_yes);
                    cost3.setBackgroundResource(R.drawable.ruble_yes);
                    break;
            }
        }else{              //Если тема тёмная
            holder.restaurant.setTextColor(Color.argb(255,255,255,255));
            holder.time.setTextColor(Color.argb(255,255,255,255));
            holder.tag1.setTextColor(Color.argb(255,179,179,179));
            holder.tag2.setTextColor(Color.argb(255,179,179,179));
            holder.stars.setTextColor(Color.argb(255,255,255,255));
            holder.walletIMG.setBackgroundResource(R.drawable.wallet_light);
            holder.poleIMG.setBackgroundResource(R.color.dark_up_back);
            holder.starsIMG.setBackgroundResource(R.drawable.star_light);
            holder.lin_time.setBackgroundResource(R.drawable.maket_time_dark);
            holder.min.setTextColor(Color.argb(255,179,179,179));
            switch (restaurants.get(position).getCost()) {
                case 1:
                    cost1.setBackgroundResource(R.drawable.ruble_yes_light);
                    cost2.setBackgroundResource(R.drawable.ruble_no_light);
                    cost3.setBackgroundResource(R.drawable.ruble_no_light);
                    break;
                case 2:
                    cost1.setBackgroundResource(R.drawable.ruble_yes_light);
                    cost2.setBackgroundResource(R.drawable.ruble_yes_light);
                    cost3.setBackgroundResource(R.drawable.ruble_no_light);
                    break;
                case 3:
                    cost1.setBackgroundResource(R.drawable.ruble_yes_light);
                    cost2.setBackgroundResource(R.drawable.ruble_yes_light);
                    cost3.setBackgroundResource(R.drawable.ruble_yes_light);
                    break;
            }
        }
           switch (restaurants.get(position).getId_img()) {
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
               case 8:
                   holder.id_img.setImageResource(R.drawable.img8);
                   break;
               case 9:
                   holder.id_img.setImageResource(R.drawable.img9);
                   break;
               case 10:
                   holder.id_img.setImageResource(R.drawable.img10);
                   break;
           }
           holder.time.setText(restaurants.get(position).getTime());

           holder.stars.setText(restaurants.get(position).getStars());
           holder.restaurant.setText(restaurants.get(position).getRestaurant());
           holder.tag1.setText(restaurants.get(position).getTag1());

           if (restaurants.get(position).getTag2().equals("none")){
               holder.tag2.setVisibility(View.INVISIBLE);
           }else{
               holder.tag2.setText(" "+restaurants.get(position).getTag2());
           }
       }
    @Override
    public int getItemCount() {return restaurants.size();}


}
