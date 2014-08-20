package com.example.icarjs.myfirsttest;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;


public class CanvasActivity extends Activity {

    private boolean gameOver = false;
    Handler mHandler = new Handler();
    Display realDisplay = null;
    Circle circle = null;
    Random random = new Random();
// ----------------------- le GameOver servira de flag pour annoncer la fin de la partie -----------
    public boolean getGameOver(){
        return this.gameOver;
    }
    public void setGameOver(boolean b){
        this.gameOver = b;
    }
//--------------------------------------------------------------------------------------------------
    public Runnable monThread = new Runnable() {
        @Override
        public void run() {

            runDrawing(); // 1 cercle par thread
            mHandler.postDelayed(monThread, 1000);// fonction permettant de rappeler le thread/second
            realDisplay.postInvalidate();// permet le rafraichissement de la view par invalidation
        }
    };

    public void runDrawing(){

        circle = new Circle();
        int x = 1600;//findViewById(R.id.canvas).getWidth();
        int y = 800;//findViewById(R.id.canvas).getHeight();
        if (x>0 || y>0) {
            circle.centerX = 50 + random.nextInt(x);// permet d'obtenir un nombre aléatoire entre
            circle.centerY = 50 + random.nextInt(y);// 50 et le nombre passer en paramètre
        } else {
            Log.e("num error", "les coordonnées du cercle sont négatives");
        }
        circle.radius = 50 + 35 * random.nextFloat();// la taille du cercle devra dépendre de la montée du score
        circle.fillColor = 0xff000000;// affectation de la couleur
        realDisplay.circles.add(circle);// ajout du cercle a la collection
    }

    // -------------- Fonction pour reduire progressivement la taille du cercle
    public void modifCircle(Circle circle){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // blocage de l'écran en mode paysage
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Display display = new Display(this);
        realDisplay = display;
        display.init();
        setContentView(display);
        monThread.run();// déclenchement des thread
    }

    static class Display extends View {

        Collection<Circle> circles = new ArrayList<Circle>();
        Circle draggedCircle = null;
        float draggedOriginalCenterX;
        float draggedOriginalCenterY;
        float draggedStartX;
        float draggedStartY;
        Random random = new Random();
        Paint backPaint;
        Paint circlePaint;

        Display(Context context) {
            super(context);
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
}

/**
 * Se renseigner sur ce que devient le singleton lors de la fermeture du canvas et de sa réouverture
 *
 */


