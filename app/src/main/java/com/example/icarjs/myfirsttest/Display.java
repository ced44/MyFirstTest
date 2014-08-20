package com.example.icarjs.myfirsttest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by icarjs on 21/08/14.
 */

/*
public class Display extends View {

    private static Display instance = null;
    Collection<Circle> circles = new ArrayList<Circle>();
    Circle draggedCircle = null;
    float draggedOriginalCenterX;
    float draggedOriginalCenterY;
    float draggedStartX;
    float draggedStartY;
    Random random = new Random();
    Paint backPaint;
    Paint circlePaint;
    Context context = null;

    protected Display(Context context) {

        super(context);
    }

    public static Display getInstance(Context context) {

        if(instance == null) {

            instance = new Display(context);
            this.context = context;
            return instance;
        }
        else {
            return instance;
        }
    }

    public Context getContextSingleton(){
        return this.context;
    }

    Circle findCircle(float x, float y) {

        Circle found = null;
        for (Circle circle : circles) {
            if (circle.contains(x, y)) {
                found = circle;
                return circle;
            }
        }
        return null;
    }

    void init() {
        backPaint = new Paint();
        backPaint.setColor(Color.WHITE);
        backPaint.setStyle(Paint.Style.FILL);
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        Circle circle=null;

        setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getActionMasked()) {
                    touchDown(
                            event.getX(event.getActionIndex()),
                            event.getY(event.getActionIndex())
                    );
                    return true;
                }
                    /*
                    if (MotionEvent.ACTION_MOVE == event.getActionMasked()) {
                        touchMove(
                                event.getX(event.getActionIndex()),
                                event.getY(event.getActionIndex())
                        );
                        return true;
                    }
                    */
/*
                if (MotionEvent.ACTION_UP == event.getActionMasked()) {
                    touchUp(
                            event.getX(event.getActionIndex()),
                            event.getY(event.getActionIndex())
                    );
                    return true;
                }
                return false;
            }

            private void touchDown(float fx, float fy) {
                Circle clickedCircle = findCircle(fx, fy);
                if (null != clickedCircle) {
                    draggedCircle = clickedCircle;
                    circles.remove(draggedCircle);
                        /*
                        draggedOriginalCenterX = draggedCircle.centerX;
                        draggedOriginalCenterY = draggedCircle.centerY;
                        draggedStartX = fx;
                        draggedStartY = fy;
                        */
/*
                } else {
                    Circle circle = new Circle();
                    circle.centerX = fx;
                    circle.centerY = fy;
                    circle.radius = 50 + 30 * random.nextFloat();
                    circle.fillColor = 0xff000000;
                    circles.add(circle);
                }
                invalidate();
            }

            /*
            private void touchMove(float fx, float fy) {
                // dragging
                if (null != draggedCircle) {
                    draggedCircle.centerX =
                            draggedOriginalCenterX - draggedStartX + fx;
                    draggedCircle.centerY =
                            draggedOriginalCenterY - draggedStartY + fy;
                    invalidate();
                }
            }
            */
/*
            private void touchUp(float fx, float fy) {
                if (null != draggedCircle) {
                    if (!draggedCircle.inView(getWidth(), getHeight())) {
                        // remove circle
                        circles.remove(draggedCircle);
                    }
                    // stop dragging
                    draggedCircle = null;
                    invalidate();
                }
            }

        });
    }

    public void drawing(Display display){
        MyThread mt = new MyThread();
        Handler myHandler = new Handler();
        for (int ii =0; ii<5; ii++) {
            myHandler.postDelayed(mt, 200);
        }
        display.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.w("warning", "onDraw");
        canvas.drawRect(0, 0, getWidth(), getHeight(), backPaint);
        for (Circle circle : circles) {
            System.out.println(circle.centerX + "  :  " + circle.centerY);
            circlePaint.setColor(circle.fillColor);
            // possibilité pour réutiliser RectF ????
            canvas.drawOval(
                    new RectF(
                            circle.centerX - circle.radius,
                            circle.centerY - circle.radius,
                            circle.centerX + circle.radius,
                            circle.centerY + circle.radius
                    ), circlePaint
            );
        }
        System.out.println(circles.size());
    }

}
*/

