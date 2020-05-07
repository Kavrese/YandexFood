package com.example.yandex_food;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MyView extends LinearLayout {

    public MyView(Context context) {
        super(context);
    }
    public MyView(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    public MyView(Context context, AttributeSet attrs,int defStyle) {
        super(context,attrs,defStyle);
    }

    public void click (Context context){
        Toast.makeText(context, "клик по MyView", Toast.LENGTH_SHORT).show();
    }

}
