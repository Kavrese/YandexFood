package com.example.yandex_food;

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
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {
    private boolean stile_light = true;
    int clickA = 0,clickB = 0,clickC = 0,clickG = 0,clickP = 0,clickR = 0,clickI = 0,clickS = 0,clickCh = 0; //Счётчики кликов у кнопок в ScrollView
    RecyclerView recyclerView;
    ImageView open_menu,search;
    DrawerLayout drawerLayout;
    ConstraintLayout con;
    TextView found;
    Button burger,children,russian,italian,pizza,great_food,avtor,chicken,sushi;
    ArrayList<Restaurants> arrayList = new ArrayList<>();
    RestaurantsAdapter restaurantsAdapter ;
    ActionBarDrawerToggle drawerToggle;
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
        chicken = findViewById(R.id.chicken);
        sushi = findViewById(R.id.sushi);
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
                        if(stile_light) {
                            Toast.makeText(MainActivity.this, "Изменение темы на тёмную", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Изменение темы на светлую", Toast.LENGTH_SHORT).show();
                        }
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
    public void onOpenDrawer (View view){
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void onSearch (View view){
        Toast.makeText(this, "Поиск", Toast.LENGTH_SHORT).show();
    }

    public void onClickScrollView (View view){
        arrayList.clear();
        switch (view.getId()){
            case R.id.italian:
                editClick("clickI");
                if(stile_light) {
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    if (clickI >= 2){
                        italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    }
                }else{
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_dark);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    if (clickI >= 2){
                        italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    }
                }
                break;
            case R.id.avtor:
                editClick("clickA");
                if(stile_light) {
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    if (clickA >= 2){
                        avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    }
                }else{
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_dark);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    if (clickA >= 2){
                        avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    }
                }
                break;
            case R.id.burger:
                editClick("clickB");
                if(stile_light) {
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    if (clickB >= 2){
                        burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    }
                }else{
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_dark);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    if (clickB >= 2){
                        burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    }
                }
                break;
            case R.id.pizza:
                editClick("clickP");
                if(stile_light) {
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    if (clickP >= 2){
                        pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    }
                }else{
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_dark);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    if (clickP >= 2){
                        pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    }
                }
                break;
            case R.id.russian:
                editClick("clickR");
                if(stile_light) {
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    if (clickR >= 2){
                        russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    }
                }else{
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_dark);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    if (clickR >= 2){
                        russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    }
                }
                break;

            case R.id.children:
                editClick("clickC");
                if(stile_light){
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    if (clickC >= 2){
                        children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    }
                     }else{
                        avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                        burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                        pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                        russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                         italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                        children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_dark);
                        great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                        sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                        sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    if (clickC >= 2){
                        children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    }
                    }
                break;
            case R.id.great_food:
                editClick("clickG");
                if(stile_light){
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);
                chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    if (clickG >= 2){
                        great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    }
                }else{
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_dark);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    if (clickG >= 2){
                        great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    }
                }

                break;

            case R.id.chicken:
                editClick("clickCh");
                if(stile_light){
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);
                    if (clickCh >= 2){
                        chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    }
                }else{
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_dark);
                    if (clickCh >= 2){
                        chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    }
                }
                break;

            case R.id.sushi:
                editClick("clickS");
                if(stile_light){
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_light);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    if (clickS >= 2){
                        sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    }
                }else{
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes_dark);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    if (clickS == 2){
                        sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    }
                }
                break;
        }
        if((clickI == 1)|| (clickS == 1) || (clickCh == 1) || (clickA == 1) || (clickG == 1) || (clickB == 1) || (clickR == 1) || (clickP == 1) || (clickC == 1)){
            addNewArrayList(view);
            recyclerView.getAdapter().notifyDataSetChanged();
        }
        if((clickI == 2)|| (clickS == 2) || (clickCh == 2) || (clickA == 2) || (clickG == 2) || (clickB == 2) || (clickR == 2) || (clickP == 2) || (clickC == 2)){
            standartArrayList();
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    public void editClick (String click){
        switch (click){
            case "clickI":
                clickI++;
                clickA = 0;
                clickB = 0;
                clickP = 0;
                clickC = 0;
                clickG = 0;
                clickR = 0;
                clickCh = 0;
                clickS = 0;
                break;
            case "clickA":
                clickI = 0;
                clickA++;
                clickB = 0;
                clickP = 0;
                clickC = 0;
                clickG = 0;
                clickR = 0;
                clickCh = 0;
                clickS = 0;
                break;
            case "clickB":
                clickI = 0;
                clickA = 0;
                clickB++;
                clickP = 0;
                clickC = 0;
                clickG = 0;
                clickR = 0;
                clickCh = 0;
                clickS = 0;
                break;
            case "clickC":
                clickI = 0;
                clickA = 0;
                clickB = 0;
                clickP = 0;
                clickC++;
                clickG = 0;
                clickR = 0;
                clickCh = 0;
                clickS = 0;
                break;
            case "clickG":
                clickI = 0;
                clickA = 0;
                clickB = 0;
                clickP = 0;
                clickC = 0;
                clickG ++;
                clickR = 0;
                clickCh = 0;
                clickS = 0;
                break;
            case "clickP":
                clickI = 0;
                clickA = 0;
                clickB = 0;
                clickP++;
                clickC = 0;
                clickG = 0;
                clickR = 0;
                clickCh = 0;
                clickS = 0;
                break;
            case "clickR":
                clickI = 0;
                clickA = 0;
                clickB = 0;
                clickP = 0;
                clickC = 0;
                clickG = 0;
                clickR++;
                clickCh = 0;
                clickS = 0;
                break;
            case "clickCh":
                clickI = 0;
                clickA = 0;
                clickB = 0;
                clickP = 0;
                clickC = 0;
                clickG = 0;
                clickR = 0;
                clickCh ++;
                clickS = 0;
                break;
            case "clickS":
                clickI = 0;
                clickA = 0;
                clickB = 0;
                clickP = 0;
                clickC = 0;
                clickG = 0;
                clickR = 0;
                clickCh= 0;
                clickS ++;
                break;
            case "return":
                clickI = 0;
                clickA = 0;
                clickB = 0;
                clickC = 0;
                clickG = 0;
                clickP = 0;
                clickR = 0;
                clickCh = 0;
                clickS = 0;
                break;
        }
    }

    public void standartArrayList (){
        arrayList.clear();
        editClick("return");

        arrayList.add(new Restaurants(1, "~20", 3, 3.5f, "Meet the Brewers","Авторская","Русская",stile_light));
        arrayList.add(new Restaurants(2, "~30", 2, 4.0f, "Wendy's","Для детей","Бургеры",stile_light));
        arrayList.add(new Restaurants(3, "~35", 1, 4.5f, "QLB","Бургеры","none",stile_light));
        arrayList.add(new Restaurants(4, "~45", 1, 3.0f, "Ваши Насти","Здоровая еда","Русская",stile_light));
        arrayList.add(new Restaurants(5, "~30", 2, 4.0f, "Domino's Pizza","Пицца","none",stile_light));
        arrayList.add(new Restaurants(6, "~75", 3, 2.0f, "Goofy's Kitchen","Пицца","Для детей",stile_light));
        arrayList.add(new Restaurants(7, "~40", 2, 4.5f, "Brook Cafe","Авторская","none",stile_light));
        arrayList.add(new Restaurants(8, "~55", 2, 4.0f, "Ташир пицца","Пицца","Итальянская",stile_light));
        arrayList.add(new Restaurants(9, "~35", 3, 5.0f, "Сушия","Суши","none",stile_light));
        arrayList.add(new Restaurants(10, "~70", 2, 4.0f, "KFC","Курица","none",stile_light));
    }

    public void addNewArrayList (View view){
        arrayList.clear();
        switch (view.getId()){
            case R.id.avtor:
                arrayList.add(new Restaurants(1, "~20", 3, 3.5f, "Meet the Brewers","Авторская","Русская",stile_light));
                arrayList.add(new Restaurants(7, "~40", 2, 4.5f, "Brook Cafe","Авторская","none",stile_light));
                break;
            case R.id.great_food:
                arrayList.add(new Restaurants(4, "~45", 1, 3.0f, "Ваши Насти","Здоровая еда","Русская",stile_light));
                break;
            case R.id.burger:
                arrayList.add(new Restaurants(2, "~30", 2, 4.0f, "Wendy's","Для детей","Бургеры",stile_light));
                arrayList.add(new Restaurants(3, "~35", 1, 4.5f, "QLB","Бургеры","none",stile_light));
                break;
            case R.id.children:
                arrayList.add(new Restaurants(2, "~30", 2, 4.0f, "Wendy's","Для детей","Бургеры",stile_light));
                arrayList.add(new Restaurants(6, "~75", 3, 2.0f, "Goofy's Kitchen","Пицца","Для детей",stile_light));
                break;
            case R.id.italian:
                arrayList.add(new Restaurants(8, "~55", 2, 4.0f, "Ташир пицца","Пицца","Итальянская",stile_light));
                break;
            case R.id.pizza:
                arrayList.add(new Restaurants(8, "~55", 2, 4.0f, "Ташир пицца","Пицца","Итальянская",stile_light));
                arrayList.add(new Restaurants(5, "~30", 2, 4.0f, "Domino's Pizza","Пицца","none",stile_light));
                arrayList.add(new Restaurants(6, "~75", 3, 2.0f, "Goofy's Kitchen","Пицца","Для детей",stile_light));
                break;
            case R.id.sushi:
                arrayList.add(new Restaurants(9, "~35", 3, 5.0f, "Сушия","Суши","none",stile_light));
                break;
            case R.id.chicken:
                arrayList.add(new Restaurants(10, "~70", 2, 4.0f, "KFC","Курица","none",stile_light));
                break;
            case R.id.russian:
                arrayList.add(new Restaurants(1, "~20", 3, 3.5f, "Meet the Brewers","Авторская","Русская",stile_light));
                arrayList.add(new Restaurants(4, "~45", 1, 3.0f, "Ваши Насти","Здоровая еда","Русская",stile_light));
                break;
        }

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
            chicken.setTextColor(getResources().getColor(R.color.text_color_dark));
            chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
            sushi.setTextColor(getResources().getColor(R.color.text_color_dark));
            sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);

        }else{      //Если тема была тёмной
            stile_light = true;
            con.setBackgroundColor(getResources().getColor(R.color.white_back_res));
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
            chicken.setTextColor(getResources().getColor(R.color.text_color_light));
            chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
            sushi.setTextColor(getResources().getColor(R.color.text_color_light));
            sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light
            );
        }
        standartArrayList();
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
