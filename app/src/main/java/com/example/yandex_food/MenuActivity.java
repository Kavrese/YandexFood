package com.example.yandex_food;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
ImageView back;
Button pop,salat,pizza,burger,chik;
HorizontalScrollView horizontalScrollView;
ImageView img1;
TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        name = findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("name"));
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
        pizza = findViewById(R.id.pizza_menu);
        burger = findViewById(R.id.bur);
        chik = findViewById(R.id.chick);
        img1 = findViewById(R.id.img1_card);
    }
    public void onClickHorView (View view){
        switch (view.getId()){
            case R.id.pop:
                refactorScrollViewButtons("pop");
                break;
            case R.id.salat:
                refactorScrollViewButtons("salat");
                break;
            case R.id.pizza_menu:
                refactorScrollViewButtons("pizza");
                break;
            case R.id.bur:
                refactorScrollViewButtons("burger");
                break;
            case R.id.chick:
                refactorScrollViewButtons("chick");
                break;
        }
    }
    public void refactorScrollViewButtons (String s){
        switch (s){
            case "pop":
                     pop.setTextColor(getResources().getColor(R.color.dark));
                pop.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                     salat.setTextColor(getResources().getColor(R.color.grey));
                salat.setBackgroundResource(R.color.white);
                      pizza.setTextColor(getResources().getColor(R.color.grey));
                pizza.setBackgroundResource(R.color.white);
                      burger.setTextColor(getResources().getColor(R.color.grey));
                burger.setBackgroundResource(R.color.white);
                      chik.setTextColor(getResources().getColor(R.color.grey));
                chik.setBackgroundResource(R.color.white);
                break;
            case "salat":
                      pop.setTextColor(getResources().getColor(R.color.grey));
                pop.setBackgroundResource(R.color.white);
                      salat.setTextColor(getResources().getColor(R.color.dark));
                salat.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                      pizza.setTextColor(getResources().getColor(R.color.grey));
                pizza.setBackgroundResource(R.color.white);
                      burger.setTextColor(getResources().getColor(R.color.grey));
                burger.setBackgroundResource(R.color.white);
                      chik.setTextColor(getResources().getColor(R.color.grey));
                chik.setBackgroundResource(R.color.white);
                break;
            case "pizza":
                    pop.setTextColor(getResources().getColor(R.color.grey));
                pop.setBackgroundResource(R.color.white);
                    salat.setTextColor(getResources().getColor(R.color.grey));
                salat.setBackgroundResource(R.color.white);
                    pizza.setTextColor(getResources().getColor(R.color.dark));
                pizza.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                    burger.setTextColor(getResources().getColor(R.color.grey));
                burger.setBackgroundResource(R.color.white);
                    chik.setTextColor(getResources().getColor(R.color.grey));
                chik.setBackgroundResource(R.color.white);
                break;
            case "burger":
                    pop.setTextColor(getResources().getColor(R.color.grey));
                pop.setBackgroundResource(R.color.white);
                    salat.setTextColor(getResources().getColor(R.color.grey));
                salat.setBackgroundResource(R.color.white);
                    pizza.setTextColor(getResources().getColor(R.color.grey));
                pizza.setBackgroundResource(R.color.white);
                    burger.setTextColor(getResources().getColor(R.color.dark));
                burger.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                    chik.setTextColor(getResources().getColor(R.color.grey));
                chik.setBackgroundResource(R.color.white);
                break;
            case "chick":
                     pop.setTextColor(getResources().getColor(R.color.grey));
                pop.setBackgroundResource(R.color.white);
                     salat.setTextColor(getResources().getColor(R.color.grey));
                salat.setBackgroundResource(R.color.white);
                    pizza.setTextColor(getResources().getColor(R.color.grey));
                pizza.setBackgroundResource(R.color.white);
                    burger.setTextColor(getResources().getColor(R.color.grey));
                burger.setBackgroundResource(R.color.white);
                    chik.setTextColor(getResources().getColor(R.color.dark));
                chik.setBackgroundResource(R.drawable.maket_button_in_scroll_view_yes);
                break;
            case "drop":
                pop.setBackgroundResource(R.color.white);
                salat.setBackgroundResource(R.color.white);
                pizza.setBackgroundResource(R.color.white);
                burger.setBackgroundResource(R.color.white);
                chik.setBackgroundResource(R.color.white);
        }
    }
}
