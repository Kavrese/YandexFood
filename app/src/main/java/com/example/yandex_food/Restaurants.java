package com.example.yandex_food;

import android.widget.ImageView;

public class Restaurants {
    private int id_img;
    private String time;
    private int cost;
    private float stars;
    private String restaurant;

    public Restaurants(int id_img, String time, int cost, float stars, String restaurant){
        this.id_img = id_img;
        this.time = time;
        this.cost = cost;
        this.stars = stars;
        this.restaurant = restaurant;
    }

    public int getId_img() {
        return this.id_img;
    }
    public String getStars() {
        String s = Float.toString(stars);
        return s;
    }
    public int getCost() {
        return this.cost;
    }
    public String getTime() {
        return this.time;
    }
    public String getRestaurant() {
        return this.restaurant;
    }
}
