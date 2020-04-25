package com.example.yandex_food;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private boolean stile_light = true;
    private boolean start = true;
    private boolean other_button = false;
    private boolean per = false;
    //Счётчики кликов по кнопке в ScrollView.   Кнопка:
    //Авторская     Бургеры     Для детей   Здоровая еда    Пицца    Русская    Итальянская     Суши      Курица
    int clickA = 0, clickB = 0, clickC = 0, clickG = 0, clickP = 0, clickR = 0, clickI = 0, clickS = 0, clickCh = 0; //Счётчики кликов у кнопок в ScrollView
    RecyclerView recyclerView;
    ImageView open_menu, search;    //Кнопки Toolbar'а
    ImageView cardURL;
    String x, y;    //Для геолокации
    LocationManager manager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SwipeRefreshLayout swipeRefreshLayout;
    DrawerLayout drawerLayout;
    ScrollView scroll2;
    HorizontalScrollView scrollCard;
    ConstraintLayout con;
    TextView found, text;
    Button burger, children, russian, italian, pizza, great_food, avtor, chicken, sushi;
    ImageButton setting;
    ArrayList<Restaurants> arrayList = new ArrayList<>();       //Данные для RecyclerView
    RestaurantsAdapter restaurantsAdapter;
    NavigationView navigationView;
    private LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {      //При обновлении
            y = (String.valueOf(location.getLongitude()));      //Определяем долготу
            x = (String.valueOf(location.getLatitude()));       //Определяем широту
            found.setText("Широта: " + x + "\nДолгота: " + y);  //Соединяем
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("0", 0);
        scroll2 = findViewById(R.id.scroll2);
        scrollCard = findViewById(R.id.scrollCard);
        scroll2.post(new Runnable() {   //Скролим SrollView до самого начала
            @Override
            public void run() {
                scroll2.fullScroll(ScrollView.FOCUS_UP);
            }
        });
        new Handler().postDelayed(new Runnable() {  //Через 1 секунд приложение должно точно всё прогрузить
            @Override
            public void run() {
                start = false;
            }
        }, 1000);
        text = findViewById(R.id.text);
        con = findViewById(R.id.con);
        navigationView = findViewById(R.id.nav_view);
        search = findViewById(R.id.search);
        recyclerView = findViewById(R.id.recyclerView);
        open_menu = findViewById(R.id.menuopen);
        drawerLayout = findViewById(R.id.drawer_layout);
        found = findViewById(R.id.found);
        setting = findViewById(R.id.setting);
        burger = findViewById(R.id.burger);
        children = findViewById(R.id.children);
        russian = findViewById(R.id.russian);
        italian = findViewById(R.id.italian);
        pizza = findViewById(R.id.pizza);
        great_food = findViewById(R.id.great_food);
        avtor = findViewById(R.id.avtor);
        chicken = findViewById(R.id.chicken);
        sushi = findViewById(R.id.sushi);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        cardURL = findViewById(R.id.URL);
        //Подготовка к определению геолокации
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); //Менеджер сервера
        //Запускаем RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantsAdapter = new RestaurantsAdapter(arrayList);         //Создаём адаптер
        recyclerView.setAdapter(restaurantsAdapter);              //Присоединяем адаптер
        standartArrayList();    //Вызываем метод что-бы заполнить RecyclerView стандартными карточками

            switch (sharedPreferences.getString("theme", "light")) {    //Если предыдущяя темы была тёмной изменяем boolean (т.к. по стандарту стоит true) и вызываем метод смены тем
                case "dark":
                    stile_light = false;
                    onSwithStile("dark"); //Метод смены тем
                    break;
                case "light":
                    stile_light = true;
                    onSwithStile("light"); //Метод смены тем
                    break;
            }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {    //Клики по боковой панели
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_enter:    //При клике на item Войти выводим Toast
                        Toast.makeText(MainActivity.this, "Вы нажали на Войти", Toast.LENGTH_SHORT).show();
                        break;
                    //Дальше так-же
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
                        if (stile_light) {
                            Toast.makeText(MainActivity.this, "Изменение темы на тёмную", Toast.LENGTH_SHORT).show();
                            onSwithStile("dark");
                        } else {
                            onSwithStile("light");
                            Toast.makeText(MainActivity.this, "Изменение темы на светлую", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.nav_exit:
                        System.exit(1);
                        break;
                }
                return false;
            }
        });
        //Настраиваем swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this);//Включаем слушатель
        swipeRefreshLayout.setColorSchemeResources(R.color.blue_dark);      //Устанавливаем цвета анимации обновления

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {  //При прокрутке RecyclerView убирает остальные элементы в ScrollView
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scroll2.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!start && !other_button) //Если приложение все прогрузило и не нажата ни одна кнопка, то при скролинге RecyclerView убираем SrollView
                            scroll2.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        });
        checkPer(); //Проверяем разрешение методом
    }
    public void checkPer (){
        //Проверка разрешения
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Разрешения нет - запрашиваем
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }else {
            per = true;                    //Говорим что разрешение есть
            onRebootLocation();          //Определение геолокации
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onRebootLocation(); //Первое определение геолокации
                } else {
                    found.setText("Предоставьте разрешение на геолокацию");
                    per = false;
                }
        }
    }

    @Override       //При обнавлении
    public void onRefresh() {
        checkPer();
        if(per) {
            found.setText("Загрузка"); //В это время происходит обнавлене геолокации
            onRebootLocation();//Метод обнавления геолокации
        }else{
            found.setText("Предоставьте разрешение на геолокацию");
        }
        new Handler().postDelayed(new Runnable() {  //Запускаем таймер на 1,5 секунд
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);// Отменяем анимацию обновления
            }
        }, 2000);
    }

    public void onOpenDrawer(View view) {  //Клик на кнопку, открывающяя боковую панель, в Toolbar
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void onClickHorScrollView(View view){
        switch (view.getId()){
            case R.id.card1:
                Toast.makeText(this, "Доставка бесплатно", Toast.LENGTH_SHORT).show();
                break;
            case R.id.URL:
                Toast.makeText(this, "Поддержи любимые рестораны на карантине", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onSearch(View view) {    //Клик на кнопку поиска в ToolBar
        Toast.makeText(this, "Поиск", Toast.LENGTH_SHORT).show();
    }

    public void onRebootLocation() {
        boolean inet = isNetworkConnected();    //Проверка включён ли Интернет
        boolean gps = isGPSConnected();         //Проверка включён ли GPS
        //Проверка подключенны ли к сети
        if (!inet) {
            found.setText("Подключитесь к сети");
        }else {
            if (!gps && per) {
                found.setText("Включите геолакацию");
            }else {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    //Проверка разрешений
                    return;
                }
                manager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, listener, null);//Запрашиваем координаты

                    new Handler().postDelayed(new Runnable() {  //Запускаем таймер на 20 сек. За это время должно произойти обновление геолокации
                        @Override
                        public void run() {
                            if (!per) {
                                found.setText("Предоставте разрешение на геолокацию");
                            }
                            if (found.getText().equals("Загрузка")) //Проверяем завершилась ли обновление
                                found.setText("Ошибка");    //Если за это время не произошло оновление, то ввыводим ошибку, но не прекращяем обновлять
                        }
                    }, 20000);
            }
        }
    }

    private boolean isNetworkConnected() {//Метод проверки интернета
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public boolean isGPSConnected() {   ////Метод проверки GPS
    boolean enabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    return enabled;
}

    public void onClickScrollView (View view){  //При клике на кнопки в ScrollView
        arrayList.clear();  //Очищяем лист для адаптера RecyclerView
        switch (view.getId()){
            case R.id.setting:
                Toast.makeText(this, "Что-то происходит", Toast.LENGTH_SHORT).show();
                break;
            case R.id.italian:      //Нажатие на кнопку Итальянская
                editClick("clickI");
                if(stile_light) {   //При светлой теме
                    //Меняем макеты кнопок на неактивный, кроме нажатой кнопки
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);   //Не активный макет
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);  //Не активный макет
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);   //Не активный макет
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light); //Не активный макет
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);//Не активный макет
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);//Не активный макет
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);      //Акивный макет
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light); //Не активный макет
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);   //Не активный макет
                    if (clickI >= 2){       //Если счетчик больше или равен 2, то делаем кнопку не активной
                        italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    }
                }else{      //При тёмной, тот же принцып
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
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
                //Остальные по таму-же принцыпу
            case R.id.avtor:        //Нажатие на кнопку Авторская
                editClick("clickA");
                if(stile_light) {
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
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
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
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
            case R.id.burger:           //Нажатие на кнопку Бургеры
                editClick("clickB");
                if(stile_light) {
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
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
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
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
            case R.id.pizza:        //Нажатие на кнопку Пицца
                editClick("clickP");
                if(stile_light) {
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
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
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
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
            case R.id.russian:      //Нажатие на кнопку Русская
                editClick("clickR");
                if(stile_light) {
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
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
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    if (clickR >= 2){
                        russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    }
                }
                break;

            case R.id.children:         //Нажатие на кнопку Для Детей
                editClick("clickC");
                if(stile_light){
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
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
                        children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                        great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                        sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                        sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    if (clickC >= 2){
                        children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    }
                    }
                break;
            case R.id.great_food:           //Нажатие на кнопку здоровая еда
                editClick("clickG");
                if(stile_light){
                avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
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
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    if (clickG >= 2){
                        great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    }
                }

                break;

            case R.id.chicken:              //Нажатие на кнопку Курица
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
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
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
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                    if (clickCh >= 2){
                        chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    }
                }
                break;

            case R.id.sushi:            //Нажатие на кнопку Суши
                editClick("clickS");
                if(stile_light){
                    avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
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
                    sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    if (clickS == 2){
                        sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark);
                    }
                }
                break;
        }
        if((clickI == 1)|| (clickS == 1) || (clickCh == 1) || (clickA == 1) || (clickG == 1) || (clickB == 1) || (clickR == 1) || (clickP == 1) || (clickC == 1)){
            other_button = true;    //При 1 клике
            addNewArrayList(view);// При 1 клике на любую кнопку, вызываем метод изменения данных с нажатой view
            recyclerView.getAdapter().notifyDataSetChanged(); //Обновляем Адаптер новыми данными
        }
        if((clickI == 2)|| (clickS == 2) || (clickCh == 2) || (clickA == 2) || (clickG == 2) || (clickB == 2) || (clickR == 2) || (clickP == 2) || (clickC == 2)){
            other_button = false ;  //При 2 клике
            standartArrayList();// Если происходить 2 клика на любую кнопку, то применяем стандартные данные (т.е. возращяемся к тому, что было)
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    public void editClick (String click){       //Принимает комманду и по этой комманде +1 в счётчик, при этом обнуляет другие счётчики
        switch (click){
            case "clickI":      // Комманда "clickI"
                clickI++;       // +1 в счётчик clickI
                clickA = 0;     //*
                clickB = 0;     //*
                clickP = 0;     //*
                clickC = 0;     //Обнуляем другие счётчики
                clickG = 0;     //*
                clickR = 0;     //*
                clickCh = 0;    //*
                clickS = 0;     //*
                break;
                //Дальше по такому-же принцыпу
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
                //Единственное, при комманде "return" сбрасываем все счётчики
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

    public void standartArrayList (){   //Меняем данные на стандартные
        arrayList.clear();       //Очищяем список
        editClick("return");     //Очищяем счётчики

        //Стандартные наборы данных
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

    public void addNewArrayList (View view){    //Изменяем данные взависимости от нажатой кнопки в ScrollView
        arrayList.clear();//Сбрасываем Лист
        switch (view.getId()){
            case R.id.avtor: // Добавляем данные при нажатой кнопке Авторская в ScroolView
                arrayList.add(new Restaurants(1, "~20", 3, 3.5f, "Meet the Brewers","Авторская","Русская",stile_light));
                arrayList.add(new Restaurants(7, "~40", 2, 4.5f, "Brook Cafe","Авторская","none",stile_light));
                break;
                //Дальше по такому-же принцыпу
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
            default:    //На всякий случай
                standartArrayList();
        }

    }

    public void onSwithStile (String theme){    //Метод изменения тем
        if (theme.equals("dark")){              //Если тема должна быть тёмной
            stile_light = false;
            editor = sharedPreferences.edit();  //
            editor.putString("theme","dark");   //  Заносим измененние темы в sharedPreferences
            editor.apply();                     //
            swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.dark_up_back)); // Меняем задний фон анимации обновления
            con.setBackgroundColor(getResources().getColor(R.color.dark));      //Изменяем цвет заднего фона
            navigationView.setBackgroundColor(getResources().getColor(R.color.dark_up_back2));     //Изменяет цвет бокового меню
            //Меняем макет и текст кнопок в ScrollView
            setting.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_dark_setting);
            setting.setImageResource(R.drawable.setting_light);
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
            text.setTextColor(getResources().getColor(R.color.white));
        }
        //Дальше по такому-же принцыпу
        if(theme.equals("light")){      //Если тема должа быть светлой
            stile_light = true;
            editor = sharedPreferences.edit();
            editor.putString("theme","light");
            editor.apply();
            swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.white));
            con.setBackgroundColor(getResources().getColor(R.color.white_back_res));
            navigationView.setBackgroundColor(getResources().getColor(R.color.white));
            setting.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light_setting);
            setting.setImageResource(R.drawable.setting);
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
            sushi.setBackgroundResource(R.drawable.maket_button_in_scroll_view_no_light);
            text.setTextColor(getResources().getColor(R.color.dark));
        }
        //Если нажата хоть одна кнопка, в ScrollVIew - не меняем данные для адаптера
        boolean bool = clickArrayList();
        if(!bool){
            standartArrayList();
        }
        recyclerView.getAdapter().notifyDataSetChanged();   //Не помню почему эта строчка здесь
        drawerLayout.closeDrawer(GravityCompat.START);      //Закрываем боковое окно при смене темы
    }
    public boolean clickArrayList () {      //Метод, который меняет данные взависимости от нажатой кнопки и меняет скин этой кнопки
        boolean bool = false;   //Если есть нажатай кнопка - true

                 if (clickA == 1) {     //Нажата кнопка Авторская
                     addNewArrayList(avtor);    //Меняем данные
                     avtor.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);//Меняем скин
                     bool = true;
                  }
                 //Дальше по такому-же ппринцыпу
                if (clickB == 1) {
                    addNewArrayList(burger);
                    burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                    bool = true;
                }
                if (clickCh == 1) {
                    addNewArrayList(chicken);
                    chicken.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                    bool = true;
                }
                if (clickC == 1) {
                    addNewArrayList(children);
                    children.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                    bool = true;
                }
                if (clickP == 1) {
                    addNewArrayList(pizza);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                    bool = true;
                }
                if (clickS == 1) {
                    addNewArrayList(sushi);
                    pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                    bool = true;
                }
                if (clickG == 1) {
                    addNewArrayList(great_food);
                    great_food.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                    bool = true;
                }
                if (clickR == 1) {
                    addNewArrayList(russian);
                    russian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                    bool = true;
                }
                if (clickI == 1) {
                    addNewArrayList(italian);
                    italian.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                    bool = true;
                }
            return bool;        //Возращяем boolean (true - была нажата любая кнопка)
        }
    }
