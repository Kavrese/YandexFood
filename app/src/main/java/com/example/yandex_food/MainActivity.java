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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements LocationListener {
    RecyclerView recyclerView;
    ImageView open_menu;
    DrawerLayout drawerLayout;
    TextView found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Restaurants> arrayList = new ArrayList<>();
        arrayList.add(new Restaurants(1, "20", 2, 3.5f, "Vegetarian"));
        arrayList.add(new Restaurants(2, "30", 3, 4.0f, "Время еды"));
        arrayList.add(new Restaurants(3, "35", 1, 2.0f, "QLB"));
        arrayList.add(new Restaurants(4, "45", 2, 4.5f, "Burger King"));

        RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(arrayList);
        recyclerView.setAdapter(restaurantsAdapter);
        found = findViewById(R.id.foundtext);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); //Менеджер определения координат
        //Проверка на разрешение
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    String h = String.valueOf(location.getLatitude());
                    String d = String.valueOf(location.getLongitude());
                    found.setText("Ширина: "+ h + "Долгота: " + d);
                    Log.d("TAG", "Широта="+location.getLatitude());
                    Log.d("TAG", "Долгота="+location.getLongitude());
                    Toast.makeText(getApplicationContext(), h+" и " + d, Toast.LENGTH_SHORT).show();
                } else {
                    found.setText("Sorry, location not found");
                }
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
        locationManager.requestSingleUpdate (LocationManager.GPS_PROVIDER, listener, null);

        open_menu = findViewById(R.id.menuopen);
        drawerLayout = findViewById(R.id.drawer_layout);
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
                }
                return false;
            }
        });
    }





    public void onSearch (View view){
        Toast.makeText(this, "Поиск", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            String h = String.valueOf(location.getLatitude());
            String d = String.valueOf(location.getLongitude());
            this.found.setText("Ширина: "+ h + "Долгота: " + d);
            Log.d("TAG", "Широта="+location.getLatitude());
            Log.d("TAG", "Долгота="+location.getLongitude());
            Toast.makeText(this, h+" и " + d, Toast.LENGTH_SHORT).show();
        } else {
            this.found.setText("Sorry, location not found");
        }
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
}
