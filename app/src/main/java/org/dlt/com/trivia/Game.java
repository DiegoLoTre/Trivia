package org.dlt.com.trivia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Game extends Activity implements View.OnClickListener {

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //player = MediaPlayer.create(this, R.raw.carol12);

        Button bGlobalization = (Button)findViewById(R.id.globalization);
        Button bDevelopment = (Button)findViewById(R.id.development);
        Button bGeography = (Button)findViewById(R.id.geography);
        Button bLeaders = (Button)findViewById(R.id.leaders);
        Button bConflicts = (Button)findViewById(R.id.conflict);
        Button bSettings = (Button)findViewById(R.id.settings);
        Button bRandom = (Button)findViewById(R.id.random);

        bGlobalization.setOnClickListener(this);
        bDevelopment.setOnClickListener(this);
        bGeography.setOnClickListener(this);
        bLeaders.setOnClickListener(this);
        bConflicts.setOnClickListener(this);
        bSettings.setOnClickListener(this);
        bRandom.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("ManagerSound", Context.MODE_PRIVATE);
        String play = prefs.getString("Sound","");
        if(play.equals("On"))
            player.start();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        if(v.getId() == R.id.globalization)    intent = new Intent(this, Globalization.class);
        else if(v.getId() == R.id.development) intent = new Intent(this, Development.class);
        else if(v.getId() == R.id.geography)   intent = new Intent(this, Geography.class);
        else if(v.getId() == R.id.leaders)     intent = new Intent(this, Leaders.class);
        else if(v.getId() == R.id.conflict)    intent = new Intent(this, Conflict.class);
        else if(v.getId() == R.id.settings)    intent = new Intent(this, Settings.class);
        else if(v.getId() == R.id.random) {
            int question = (int)(Math.random()*(5-1)+1);

            if(question == 1)    intent = new Intent(this, Globalization.class);
            else if(question == 2) intent = new Intent(this, Development.class);
            else if(question == 3)   intent = new Intent(this, Geography.class);
            else if(question == 4)     intent = new Intent(this, Leaders.class);
            else if(question == 5)    intent = new Intent(this, Conflict.class);
            else intent = new Intent(this, Settings.class);

            startActivity(intent);

        }

        if(intent != null)
            startActivity(intent);

    }
}
