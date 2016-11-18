package org.dlt.com.trivia;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Settings extends Activity implements View.OnClickListener{

    String function = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button bOn = (Button) findViewById(R.id.on);
        bOn.setOnClickListener(this);

        Button bOff = (Button)findViewById(R.id.off);
        bOff.setOnClickListener(this);
    }

    public void saveConfiguration() {
        SharedPreferences prefs = getSharedPreferences("ManagerSound", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Sound",function);
        editor.apply();
    }

    public void loadConfiguration() {
        SharedPreferences prefs = getSharedPreferences("ManagerSound", Context.MODE_PRIVATE);
        function = prefs.getString("Sound","On");
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadConfiguration();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveConfiguration();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.on) {
            function = "On";
            Toast.makeText(getApplicationContext(),"On",Toast.LENGTH_SHORT).show();
        } else if(v.getId() == R.id.off) {
            function = "Off";
            Toast.makeText(getApplicationContext(),"Off",Toast.LENGTH_SHORT).show();
        }
    }
}