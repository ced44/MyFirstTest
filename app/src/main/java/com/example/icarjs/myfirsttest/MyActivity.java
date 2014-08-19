package com.example.icarjs.myfirsttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity {

    /*
    View.OnClickListener myHandler1 = new View.OnClickListener(){
        public void changeView(){
            startActivity(intent);
        }
    };
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        /*
        Button buttonPlay = (Button)findViewById(R.id.button);
        Button buttonOptions = (Button)findViewById(R.id.button2);
        Button buttonScores = (Button)findViewById(R.id.button3);
    */

        //ButtonPlay.setOnClickListener(myHandler1);
    }

    public void changeView(View view){
        Intent intent1 = new Intent(this, CanvasActivity.class);
        this.startActivity(intent1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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

    public void onBtn_optionClick (View v){
       Intent optionsActivity = new Intent(MyActivity.this, Options.class);
        startActivity(optionsActivity);
    }
}
