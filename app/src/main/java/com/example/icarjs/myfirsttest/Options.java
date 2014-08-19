package com.example.icarjs.myfirsttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;


public class Options extends Activity {

    private CheckBox sound;
    private CheckBox music;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        sound   = (CheckBox) findViewById(R.id.chck_sound);
        music = (CheckBox) findViewById(R.id.chck_music);
        back = (Button) findViewById(R.id.btn_back);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options, menu);
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

    public void onCheck_sound (View v) {
        StringBuffer texte = new StringBuffer();
        texte.append("sound").append(sound.isChecked());

        Toast.makeText(
                Options.this,
                texte.toString(),
                Toast.LENGTH_LONG
        ).show();
    }

    public void onCheck_music (View v) {
        StringBuffer texte2 = new StringBuffer();

        texte2.append("music:").append(music.isChecked());

        Toast.makeText(
                Options.this,
                texte2.toString(),
                Toast.LENGTH_LONG
        ).show();
    }
}
