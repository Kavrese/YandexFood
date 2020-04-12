package com.example.yandex_food;

public class Restaurants {
    private int id_img;
    private String time;
    private int cost;
    private float stars;
    private String restaurant,tag1,tag2;

    public Restaurants(int id_img, String time, int cost, float stars, String restaurant,String tag1,String tag2){
        this.id_img = id_img;
        this.time = time;
        this.cost = cost;
        this.stars = stars;
        this.restaurant = restaurant;
        this.tag1 = tag1;
        this.tag2 = tag2;
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

    public String getTag2() {
        return tag2;
    }

    public String getTag1() {
        return tag1;
    }
}
