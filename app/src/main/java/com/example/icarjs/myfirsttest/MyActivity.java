package com.example.icarjs.myfirsttest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity {

    private Intent intent_about;
    private Intent intent_score;
    private Intent intent_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        intent_about = new Intent(MyActivity.this, About.class);
        intent_score = new Intent(MyActivity.this, Scores.class);
        intent_settings = new Intent(MyActivity.this, Options.class);
    }

    public void changeView(View view){
        Intent intent1 = new Intent(this, MyActivity.class);
        this.startActivity(intent1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                onBackPressed();
            case R.id.menu_about:
                startActivity(intent_about);
                return true;
            case R.id.menu_score:
                startActivity(intent_score);
                return true;
            case R.id.menu_settings:
                startActivity(intent_settings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}