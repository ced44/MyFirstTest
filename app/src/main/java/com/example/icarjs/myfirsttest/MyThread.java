package com.example.icarjs.myfirsttest;

import android.view.Display;
import android.view.View;

/**
 * Created by icarjs on 20/08/14.
 */
public class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println("hello");
        Circle circle = new Circle();
        circle.centerX = (float)Math.random() * 100;
        circle.centerY = (float)Math.random() * 100;
        circle.radius = 50;
        circle.fillColor = 0xff000000;
        //Display display =(View)findViewById(R.id.canvas);


    }
}
