package com.example.yandex_food;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {
    int clickA = 0,clickB = 0,clickC = 0,clickG = 0,clickP = 0,clickR = 0;
    RecyclerView recyclerView;
    ImageView open_menu;
    DrawerLayout drawerLayout;
    TextView found;
    Button burger,children,russian,italian,pizza,great_food,avtor;
    ArrayList<Restaurants> arrayList = new ArrayList<>();
    RestaurantsAdapter restaurantsAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);

                clickA++;
                clickB = 0;
                clickP = 0;
                clickC = 0;
                clickG = 0;
                clickR = 0;

                arrayList.add(new Restaurants(1, "~20", 3, 3.5f, "Meet the Brewers","Авторская","Русская"));
                arrayList.add(new Restaurants(7, "~40", 2, 4.5f, "Brook Cafe","Авторская","none"));
                if (clickA >= 2){
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                    standartArrayList();
                }
                break;
            case R.id.burger:
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);

                clickA = 0;
                clickB++;
                clickP = 0;
                clickC = 0;
                clickG = 0;
                clickR = 0;

                arrayList.add(new Restaurants(2, "~30", 2, 4.0f, "Wendy's","Для детей","Бургеры"));
                arrayList.add(new Restaurants(3, "~35", 1, 4.5f, "QLB","Бургеры","none"));
                if (clickB >= 2){
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                    standartArrayList();
                }
                break;
            case R.id.pizza:
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);

                clickA = 0;
                clickB = 0;
                clickP++;
                clickC = 0;
                clickG = 0;
                clickR = 0;

                arrayList.add(new Restaurants(5, "~30", 2, 4.0f, "Domino's Pizza","Пицца","none"));
                arrayList.add(new Restaurants(6, "~75", 3, 2.0f, "Goofy's Kitchen","Пицца","Для детей"));
                if (clickP >= 2){
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                    standartArrayList();
                }
                break;
            case R.id.russian:
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);

                clickA = 0;
                clickB = 0;
                clickP = 0;
                clickC = 0;
                clickG = 0;
                clickR++;

                arrayList.add(new Restaurants(1, "~20", 3, 3.5f, "Meet the Brewers","Авторская","Русская"));
                arrayList.add(new Restaurants(4, "~45", 1, 3.0f, "Ваши Насти","Здоровая еда","Русская"));
                if (clickR >= 2){
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                    standartArrayList();
                }
                break;
            case R.id.children:
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);

                clickA = 0;
                clickB = 0;
                clickP = 0;
                clickC++;
                clickG = 0;
                clickR = 0;

                arrayList.add(new Restaurants(2, "~30", 2, 4.0f, "Wendy's","Для детей","Бургеры"));
                arrayList.add(new Restaurants(6, "~75", 3, 2.0f, "Goofy's Kitchen","Пицца","Для детей"));
                if (clickC >= 2){
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                    standartArrayList();
                }
                break;
            case R.id.great_food:
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);

                clickA = 0;
                clickB = 0;
                clickP = 0;
                clickC = 0;
                clickG ++;
                clickR = 0;

                arrayList.add(new Restaurants(4, "~45", 1, 3.0f, "Ваши Насти","Здоровая еда","Русская"));
                if (clickG >= 2){
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no);
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
        arrayList.add(new Restaurants(1, "~20", 3, 3.5f, "Meet the Brewers","Авторская","Русская"));
        arrayList.add(new Restaurants(2, "~30", 2, 4.0f, "Wendy's","Для детей","Бургеры"));
        arrayList.add(new Restaurants(3, "~35", 1, 4.5f, "QLB","Бургеры","none"));
        arrayList.add(new Restaurants(4, "~45", 1, 3.0f, "Ваши Насти","Здоровая еда","Русская"));
        arrayList.add(new Restaurants(5, "~30", 2, 4.0f, "Domino's Pizza","Пицца","none"));
        arrayList.add(new Restaurants(6, "~75", 3, 2.0f, "Goofy's Kitchen","Пицца","Для детей"));
        arrayList.add(new Restaurants(7, "~40", 2, 4.5f, "Brook Cafe","Авторская","none"));
    }
}
