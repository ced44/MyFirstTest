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
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;


public class CanvasActivity extends Activity {

    private boolean gameOver = false;
    Handler mHandler = new Handler();
    Handler mHandler2 = new Handler();
    Handler mHandler3 = new Handler();
    Handler mHandler4 = new Handler();
    //ModifierCircleTask mct = new ModifierCircleTask();
    DisplayView realDisplay = null;
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
            if(!getGameOver()) {
                mHandler.postDelayed(monThread, 4000);

                System.out.println("Mon Thread ressemble à ça  ===> ");
                System.out.println("Mon Thread ressemble à ça  ===> "+monThread.toString());
                realDisplay.postInvalidate();// permet le rafraichissement de la view par invalidation

                System.out.println(" ------------------je suis passé par monThread !! --------------------");
                runDrawing(); // 1 cercle par thread

            }
            else {
                realDisplay.circles.clear();
                Context context = getApplicationContext();
                CharSequence text = "GAME OVER !!!!! ";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

        }
    };
    public Runnable Thread2 = new Runnable() {
        @Override
        public void run() {
            if(!getGameOver()) {
                modifCircle(); // 1 cercle par thread
                mHandler2.postDelayed(Thread2, 500);// fonction permettant de rappeler le thread/second
                realDisplay.postInvalidate();// permet le rafraichissement de la view par invalidation
            }
        }
    };
    /*
    public Runnable Thread3 = new Runnable() {
        @Override
        public void run() {

            for (Circle circle : realDisplay.circles){
                if(circle.radius < 2){
                    realDisplay.circles.remove(circle);
                    System.out.println("Un cercle a été supprimer : "+realDisplay.circles.size());
                }
            }
            mHandler4.postDelayed(Thread3, 500);// fonction permettant de rappeler le thread/second
            realDisplay.postInvalidate();// permet le rafraichissement de la view par invalidation
        }
    };
    */

/*
    class ModifierCircleTask extends AsyncTask<String, Void, Boolean> {
        protected Boolean doInBackground(String ... x) {
            while (getGameOver() != true) {
                Thread2.run();
            }
            return true;
        }
    }
*/




    public void runDrawing(){

        circle = new Circle();
        int x = DisplayView.getInstance(DisplayView.context).getWidth();//1600;//findViewById(R.id.canvas).getWidth();
        int y = DisplayView.getInstance(DisplayView.context).getHeight();//800;//findViewById(R.id.canvas).getHeight();
        System.out.println("largeur d'écran  =================> "+x);
        System.out.println("longueur d'écran  =================> "+y);
        if (x>0 || y>0) {
            circle.centerX = random.nextInt(x) - 80;// permet d'obtenir un nombre aléatoire entre
            circle.centerY = random.nextInt(y) - 80;// 50 et le nombre passer en paramètre
        } else {
            Log.e("num error", "les coordonnées du cercle sont négatives");
        }
        circle.radius = 50 + 35 * random.nextFloat();// la taille du cercle devra dépendre de la montée du score
        circle.fillColor = 0xff000000;// affectation de la couleur
        realDisplay.circles.add(circle);// ajout du cercle a la collection
        System.out.println("un cercle a été créer !!!!!");
        realDisplay.postInvalidate();// permet le rafraichissement de la view par invalidation

        //mct.doInBackground();
        Thread2.run();
        //mHandler3.postDelayed(Thread2, 1000);





    }

    // -------------- Fonction pour reduire progressivement la taille du cercle
    public void modifCircle(){

        class Thread3 implements Runnable{

            Circle circle = null;

            public Thread3(Circle circle){
                this.circle = circle;
            }

            @Override
            public void run() {
                realDisplay.circles.remove(circle);
            }
        }

        for (Circle circle : realDisplay.circles){
            circle.radius -= 1;


            if(circle.radius < 2){
                if(!getGameOver()) {
                    Thread3 thread3 = new Thread3(circle);
                    mHandler4.postDelayed(thread3, 1000);
                    System.out.println("Un cercle a été supprimer : " + realDisplay.circles.size());
                    this.setGameOver(true);
                }
            }


        }
        realDisplay.postInvalidate();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //System.out.println("information sur la longueur de l'écran  : "+Display.getInstance(Display.context).getWidth());
        //System.out.println("information sur la largeur de l'écran  : "+Display.getInstance(Display.context).getHeight());
        // blocage de l'écran en mode paysage
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        realDisplay = DisplayView.getInstance(this);//Display.getInstance(this);//new Display(this);
        realDisplay.init();
        ViewGroup parent = (ViewGroup) realDisplay.getParent();
        if (parent != null){
            parent.removeAllViews();
        }
        setContentView(realDisplay);
        //mHandler.postDelayed(monThread, 1000);// déclenchement des thread
        System.out.println(" //////////////////  RUN thread RUUUNNNNNN !!!! //////////////////////");

        monThread.run();
    }


}

/**
 * Se renseigner sur ce que devient le singleton lors de la fermeture du canvas et de sa réouverture
 *
 */


