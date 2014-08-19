package com.example.icarjs.myfirsttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Scores extends Activity {

    private Integer TheBestSCore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        Intent CanvasActivity = getIntent();
        Integer scoreCourant = CanvasActivity.getIntExtra("value",0);
        Integer TheBestSCore = getBestScore(scoreCourant);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.scores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Integer getBestScore(Integer score){
        Integer the_best_score = 0;
        Integer db_BestScore = load_db_bestScore();
        if(db_BestScore < score){
            the_best_score = score;
        }
        return the_best_score;
    }

    private Integer load_db_bestScore(){
        Integer db_BestScore = 0;

        //code qui recup le score de la base

        return db_BestScore;
    }

}
