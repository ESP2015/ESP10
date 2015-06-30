package com.example.maxwe_000.bobthepirate;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Mainmenu extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        //View newButton = findViewById(R.id.game_button);
        //newButton.setOnClickListener((View.OnClickListener) this);
        //View aboutButton = findViewById(R.id.about_button);
        //aboutButton.setOnClickListener((View.OnClickListener) this);
        //View exitButton = findViewById(R.id.help_button);
        //exitButton.setOnClickListener((View.OnClickListener) this);
    }



    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_button:
                Intent i = new Intent(this, About.class);
                startActivity(i);
                break;
            // More buttons go here (if any) ...
            case R.id.game_button:
                Intent p = new Intent(this, Gamestart.class);
                startActivity(p);
                break;
            case R.id.help_button:
                Intent l = new Intent(this, Help.class);
                startActivity(l);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
