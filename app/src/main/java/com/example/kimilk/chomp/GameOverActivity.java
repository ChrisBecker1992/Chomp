package com.example.kimilk.chomp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity implements View.OnClickListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        findViewById(R.id.restart).setOnClickListener(this);

        int looser = getIntent().getIntExtra("looser", 1);

        TextView winnerView = (TextView) findViewById(R.id.winner);
        TextView looserView = (TextView) findViewById(R.id.looser);


        looserView.setText((String) looserView.getText() + looser);
        winnerView.setText((String) winnerView.getText() + (looser == 1 ? 2 :1));
    }


    public void onClick(View v) {


        Intent intent;

        intent = new Intent(this,StartActivity.class);

        startActivity(intent);



    }



    }






