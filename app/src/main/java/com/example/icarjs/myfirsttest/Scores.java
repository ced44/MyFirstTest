package com.example.icarjs.myfirsttest;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleCursorAdapter;


public class Scores extends ListActivity {

    private ScoreDAO data_score;
    private Cursor cListeScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scores);

        data_score = new ScoreDAO(this);
        data_score.open(this);
        cListeScore = data_score.fetchAllScores();


        SimpleCursorAdapter adapter = new
                SimpleCursorAdapter(
                  this,
                  android.R.layout.list_content,
                  cListeScore,
                  new String[] {"nom"},
                  new int[] {R.id.nom,},
                  1
                );

        setListAdapter(adapter);

        startManagingCursor(cListeScore);

        cListeScore.requery();


        Intent CanvasActivity = getIntent();
        Integer scoreCourant = CanvasActivity.getIntExtra("value",0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Integer getBestScore(Integer score){
        Integer the_best_score = 0;

        return the_best_score;
    }

    private Integer load_db_bestScore(Context context){
        Integer db_BestScore = 0;

        return db_BestScore;
    }
}
