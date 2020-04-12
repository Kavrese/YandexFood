package com.example.yandex_food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplachScreenActivity extends AppCompatActivity {
ImageView icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);
        icon = findViewById(R.id.icon);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.anim_icon_splach_screen);
        icon.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(SplachScreenActivity.this,MainActivity.class);
                startActivity(in);
                finish();
            }
        },1650);
    }
}
