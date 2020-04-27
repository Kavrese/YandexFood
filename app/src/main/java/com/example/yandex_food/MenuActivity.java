package com.example.yandex_food;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {
ImageView back;
Button pop,salat,pizza,burger,chik;
HorizontalScrollView horizontalScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        back = findViewById(R.id.back_menu);

        back.setOnClickListener(new View.OnClickListener() {        //Клик на кнопку назад в toolbar'е
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        horizontalScrollView = findViewById(R.id.hor);
        pop = findViewById(R.id.pop);
        salat = findViewById(R.id.salat);
    }
}
