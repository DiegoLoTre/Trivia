package org.dlt.com.trivia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class Globalization extends Activity implements View.OnClickListener {

    private TextView tvQuestion;
    private Button bA, bB, bC, bD;
    private Connection connection = new Connection();

    private int question = 1, score = 0;

    private String resp = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_globalization);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        tvQuestion = (TextView) findViewById(R.id.question);

        bA = (Button) findViewById(R.id.A);
        bB = (Button) findViewById(R.id.B);
        bC = (Button) findViewById(R.id.C);
        bD = (Button) findViewById(R.id.D);

        bA.setOnClickListener(this);
        bB.setOnClickListener(this);
        bC.setOnClickListener(this);
        bD.setOnClickListener(this);

        searchQuestions();
    }

    private void searchQuestions() {
        connection.setUpConnection();

        connection.sendData("searchQuestion");
        connection.sendData("globalization_"+question);

        String response = connection.receiveData();

        connection.closeConnection();

        StringTokenizer st = new StringTokenizer(response, "_");

        tvQuestion.setText(st.nextToken());
        bA.setText(st.nextToken());
        bB.setText(st.nextToken());
        bC.setText(st.nextToken());
        bD.setText(st.nextToken());

        resp = st.nextToken();

        setClickable(true);
    }

    private void setClickable(boolean bool) {
        bA.setClickable(bool);
        bB.setClickable(bool);
        bC.setClickable(bool);
        bD.setClickable(bool);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.A) { checkCorrect("A",bA); checkQuestion();}
        else if(v.getId() == R.id.B) {checkCorrect("B",bB); checkQuestion();}
        else if(v.getId() == R.id.C) {checkCorrect("C",bC); checkQuestion();}
        else if(v.getId() == R.id.D) {checkCorrect("D",bD); checkQuestion();}
    }

    private void checkQuestion() {
        question++;
        setClickable(false);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                bA.setTextColor(Color.parseColor("#FFFFFF"));
                bB.setTextColor(Color.parseColor("#FFFFFF"));
                bC.setTextColor(Color.parseColor("#FFFFFF"));
                bD.setTextColor(Color.parseColor("#FFFFFF"));
                if(question > 2) {
                    Intent end = new Intent(Globalization.this, End.class);
                    end.putExtra("score",score+" puntos");
                    score = 0;
                    startActivity(end);
                } else
                    searchQuestions();
            }
        },900L);
    }

    private void checkCorrect(String response, Button button) {
        if(resp.equals(response)) {
            score += 10;
            button.setTextColor(Color.parseColor("#7CFC00"));
        } else {
            button.setTextColor(Color.parseColor("#FF0000"));
            if(resp.equals("A"))
                bA.setTextColor(Color.parseColor("#7CFC00"));
            else if(resp.equals("B"))
                bB.setTextColor(Color.parseColor("#7CFC00"));
            else if(resp.equals("C"))
                bC.setTextColor(Color.parseColor("#7CFC00"));
            else if(resp.equals("D"))
                bD.setTextColor(Color.parseColor("#7CFC00"));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Toast.makeText(getApplicationContext(), "No puedes regresar",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    public void onStop() {
        super.onStop();
        this.finish();
    }
}
