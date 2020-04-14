package com.example.yandex_food;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {
    private boolean stile_light = true;
    int clickA = 0,clickB = 0,clickC = 0,clickG = 0,clickP = 0,clickR = 0; //Счётчики кликов у кнопок в ScrollView
    RecyclerView recyclerView;
    ImageView open_menu,search;
    DrawerLayout drawerLayout;
    ConstraintLayout con;
    TextView found;
    Button burger,children,russian,italian,pizza,great_food,avtor;
    ArrayList<Restaurants> arrayList = new ArrayList<>();
    RestaurantsAdapter restaurantsAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con = findViewById(R.id.con);
        search = findViewById(R.id.search);
        recyclerView = findViewById(R.id.recyclerView);
        open_menu = findViewById(R.id.menuopen);
        drawerLayout = findViewById(R.id.drawer_layout);
        found = findViewById(R.id.foundtext);
        burger = findViewById(R.id.burger);
        children = findViewById(R.id.children);
        russian = findViewById(R.id.russian);
        italian = findViewById(R.id.italian);
        pizza = findViewById(R.id.pizza);
        great_food = findViewById(R.id.great_food);
        avtor = findViewById(R.id.avtor);

        standartArrayList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantsAdapter = new RestaurantsAdapter(arrayList);
        recyclerView.setAdapter(restaurantsAdapter);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_enter:
                        Toast.makeText(MainActivity.this, "Вы нажали на Войти", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_support:
                        Toast.makeText(MainActivity.this, "Вы нажали на Связаться с нами", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_man:
                        Toast.makeText(MainActivity.this, "Вы нажали на Стать курьером", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_about:
                        Toast.makeText(MainActivity.this, "Вы нажали на О сервисе ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_stile:
                        Toast.makeText(MainActivity.this, "Скоро добавлю изменение темы", Toast.LENGTH_SHORT).show();
                        onSwithStile();
                        break;
                    case R.id.nav_exit:
                        System.exit(1);
                        break;
                }
                return false;
            }
        });
    }


    public void onSearch (View view){
        Toast.makeText(this, "Поиск", Toast.LENGTH_SHORT).show();
    }

    public void onClickScrollView (View view){

        arrayList.clear();
        switch (view.getId()){
            case R.id.avtor:
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);

                clickA++;
                clickB = 0;
                clickP = 0;
                clickC = 0;
                clickG = 0;
                clickR = 0;

                arrayList.add(new Restaurants(1, "~20", 3, 3.5f, "Meet the Brewers","Авторская","Русская",stile_light));
                arrayList.add(new Restaurants(7, "~40", 2, 4.5f, "Brook Cafe","Авторская","none",stile_light));
                if (clickA >= 2){
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    standartArrayList();
                }
                break;
            case R.id.burger:
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);

                clickA = 0;
                clickB++;
                clickP = 0;
                clickC = 0;
                clickG = 0;
                clickR = 0;

                arrayList.add(new Restaurants(2, "~30", 2, 4.0f, "Wendy's","Для детей","Бургеры",stile_light));
                arrayList.add(new Restaurants(3, "~35", 1, 4.5f, "QLB","Бургеры","none",stile_light));
                if (clickB >= 2){
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    standartArrayList();
                }
                break;
            case R.id.pizza:
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);

                clickA = 0;
                clickB = 0;
                clickP++;
                clickC = 0;
                clickG = 0;
                clickR = 0;

                arrayList.add(new Restaurants(5, "~30", 2, 4.0f, "Domino's Pizza","Пицца","none",stile_light));
                arrayList.add(new Restaurants(6, "~75", 3, 2.0f, "Goofy's Kitchen","Пицца","Для детей",stile_light));
                if (clickP >= 2){
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    standartArrayList();
                }
                break;
            case R.id.russian:
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);

                clickA = 0;
                clickB = 0;
                clickP = 0;
                clickC = 0;
                clickG = 0;
                clickR++;

                arrayList.add(new Restaurants(1, "~20", 3, 3.5f, "Meet the Brewers","Авторская","Русская",stile_light));
                arrayList.add(new Restaurants(4, "~45", 1, 3.0f, "Ваши Насти","Здоровая еда","Русская",stile_light));
                if (clickR >= 2){
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    standartArrayList();
                }
                break;
            case R.id.children:
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);

                clickA = 0;
                clickB = 0;
                clickP = 0;
                clickC++;
                clickG = 0;
                clickR = 0;

                arrayList.add(new Restaurants(2, "~30", 2, 4.0f, "Wendy's","Для детей","Бургеры",stile_light));
                arrayList.add(new Restaurants(6, "~75", 3, 2.0f, "Goofy's Kitchen","Пицца","Для детей",stile_light));
                if (clickC >= 2){
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    standartArrayList();
                }
                break;
            case R.id.great_food:
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);

                clickA = 0;
                clickB = 0;
                clickP = 0;
                clickC = 0;
                clickG ++;
                clickR = 0;

                arrayList.add(new Restaurants(4, "~45", 1, 3.0f, "Ваши Насти","Здоровая еда","Русская",stile_light));
                if (clickG >= 2){
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    standartArrayList();
                }
                break;
        }
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void standartArrayList (){
        clickA = 0;
        clickB = 0;
        clickC = 0;
        clickG = 0;
        clickP = 0;
        clickR = 0;
        arrayList.clear();
        arrayList.add(new Restaurants(1, "~20", 3, 3.5f, "Meet the Brewers","Авторская","Русская",stile_light));
        arrayList.add(new Restaurants(2, "~30", 2, 4.0f, "Wendy's","Для детей","Бургеры",stile_light));
        arrayList.add(new Restaurants(3, "~35", 1, 4.5f, "QLB","Бургеры","none",stile_light));
        arrayList.add(new Restaurants(4, "~45", 1, 3.0f, "Ваши Насти","Здоровая еда","Русская",stile_light));
        arrayList.add(new Restaurants(5, "~30", 2, 4.0f, "Domino's Pizza","Пицца","none",stile_light));
        arrayList.add(new Restaurants(6, "~75", 3, 2.0f, "Goofy's Kitchen","Пицца","Для детей",stile_light));
        arrayList.add(new Restaurants(7, "~40", 2, 4.5f, "Brook Cafe","Авторская","none",stile_light));
    }

    public void onSwithStile (){
        if (stile_light){   //Если тема была светлой
            stile_light = false;
            con.setBackgroundColor(getResources().getColor(R.color.dark));
            burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
            burger.setTextColor(getResources().getColor(R.color.text_color_dark));
            avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
            avtor.setTextColor(getResources().getColor(R.color.text_color_dark));
            russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
            russian.setTextColor(getResources().getColor(R.color.text_color_dark));
            great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
            great_food.setTextColor(getResources().getColor(R.color.text_color_dark));
            italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
            italian.setTextColor(getResources().getColor(R.color.text_color_dark));
            children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
            children.setTextColor(getResources().getColor(R.color.text_color_dark));
            pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
            pizza.setTextColor(getResources().getColor(R.color.text_color_dark));

        }else{      //Если тема была тёмной
            stile_light = true;
            con.setBackgroundColor(getResources().getColor(R.color.white));
            burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
            burger.setTextColor(getResources().getColor(R.color.text_color_light));
            avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
            avtor.setTextColor(getResources().getColor(R.color.text_color_light));
            russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
            russian.setTextColor(getResources().getColor(R.color.text_color_light));
            great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
            great_food.setTextColor(getResources().getColor(R.color.text_color_light));
            italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
            italian.setTextColor(getResources().getColor(R.color.text_color_light));
            children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
            children.setTextColor(getResources().getColor(R.color.text_color_light));
            pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
            pizza.setTextColor(getResources().getColor(R.color.text_color_light));
        }
    }
}
