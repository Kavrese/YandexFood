package com.example.yandex_food;

public class Restaurants {
    private int id_img;
    private int time;
    private int cost;
    private float stars;
    private String restaurant;

    public Restaurants(int id_img,int time,int cost, float stars, String restaurant){
        this.id_img = id_img;
        this.time = time;
        this.cost = cost;
        this.stars = stars;
        this.restaurant = restaurant;
    }

    public int getId_img() {
        return id_img;
    }
    public float getStars() {
        return stars;
    }
    public int getCost() {
        return cost;
    }
    public int getTime() {
        return time;
    }
    public String getRestaurant() {
        return restaurant;
    }
}
