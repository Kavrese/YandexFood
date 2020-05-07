package com.example.yandex_food;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class TestActivity extends AppCompatActivity {
    MyView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        test = findViewById(R.id.test);
    }
    public void onClick (View view){
        test.click(TestActivity.this);
    }
}
