package com.example.icarjs.myfirsttest;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundsMap;
    int SOUND1=1;
    int SOUND2=2;
    private String url ="https://ia700600.us.archive.org/14/items/TetrisThemeMusic/Tetris.ogg";
    private MediaPlayer mp = new MediaPlayer();

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
                mHandler.postDelayed(monThread, 5000);

                System.out.println("Mon Thread ressemble à ça  ===> ");
                System.out.println("Mon Thread ressemble à ça  ===> "+monThread.toString());
                realDisplay.invalidate();// permet le rafraichissement de la view par invalidation

                System.out.println(" ------------------je suis passé par monThread !! --------------------");
                runDrawing(); // 1 cercle par thread

            }
            else {
                playSound(SOUND1, 1);
                mHandler.removeCallbacks(monThread);
                realDisplay.circles.clear();
                Context context = getApplicationContext();
                CharSequence text = "GAME OVER !!!!! ";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                realDisplay.postInvalidate();
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
        int x = realDisplay.getWidth()-200;//1600;//findViewById(R.id.canvas).getWidth();
        int y = realDisplay.getHeight()-200;//800;//findViewById(R.id.canvas).getHeight();
        System.out.println("largeur d'écran  =================> "+x);
        System.out.println("longueur d'écran  =================> "+y);
        if (x>0 || y>0) {
                do{
                    circle.centerX =  ((float)Math.random()*x);// permet d'obtenir un nombre aléatoire entre
                    circle.centerY =  ((float)Math.random()*y);// 50 et le nombre passer en paramètre
                    System.out.println("placement cerle en X  =================> "+circle.centerX);
                    System.out.println("placement cerle en Y  =================> "+circle.centerY);
                }
                while (findCircle2(circle.centerX, circle.centerY));

        }
        else {
            Log.e("num error", "les coordonnées du cercle sont négatives");
        }

                    circle.radius = 50 + 35* random.nextFloat();// la taille du cercle devra dépendre de la montée du score
                    circle.fillColor = 0xff000000;// affectation de la couleur
                    realDisplay.circles.add(circle);// ajout du cercle a la collection
                    System.out.println("un cercle a été créer !!!!!");
                    realDisplay.postInvalidate();// permet le rafraichissement de la view par invalidation
                    //Thread2.run();
                   mHandler3.postDelayed(Thread2, 1000);

    }


    public boolean findCircle2(float x, float y) {

        Circle found = null;
        for (Circle circle : realDisplay.circles) {
            if (circle.contains(x, y)) {

                return true;
            }
        }
        return false;
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
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActionBar().hide();
        realDisplay = DisplayView.getInstance(this);//Display.getInstance(this);//new Display(this);
        realDisplay.init();
        ViewGroup parent = (ViewGroup) realDisplay.getParent();
        if (parent != null){
            parent.removeAllViews();
        }
        setContentView(realDisplay);
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundsMap = new HashMap<Integer, Integer>();
        soundsMap.put(SOUND1, soundPool.load(this, R.raw.tetris2, 1));
        System.out.println("-------------------------" + soundsMap.get(1) + "------------------------");
        //soundsMap.put(SOUND2, soundPool.load(this, R.raw.mafia2, 1));
        //mHandler.postDelayed(monThread, 1000);// déclenchement des thread
        System.out.println(" //////////////////  RUN thread RUUUNNNNNN !!!! //////////////////////");

        monThread.run();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        /*
        try {
            mp.setDataSource(url);
            //mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();

        }

        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                playSound(SOUND1, 1);
            }
        });
        mp.prepareAsync();
        */
        //playSound(SOUND1, 1);
    }



    public void playSound(int sound, float fSpeed) {
        AudioManager mgr = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = streamVolumeCurrent / streamVolumeMax;

        soundPool.play(soundsMap.get(sound), volume, volume, 1, 0, fSpeed);
    }





}

/**
 * Se renseigner sur ce que devient le singleton lors de la fermeture du canvas et de sa réouverture
 *
 */


