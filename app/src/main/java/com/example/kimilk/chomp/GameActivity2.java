package com.example.kimilk.chomp;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class GameActivity2 extends AppCompatActivity implements View.OnClickListener  {

    protected int player = 1;
    protected int mode;
    protected GridLayout grid;
    protected TextView playerView;
    protected Piece[] pieces = new Piece[35];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        getSupportActionBar().hide();


      mode = getIntent().getIntExtra("mode", 2);
        Log.i("GameActivity2", "mode = " + mode);

        playerView = (TextView) findViewById(R.id.player); // Referenz auf TextView
        grid = (GridLayout) findViewById(R.id.grid);

        grid.setColumnCount(5); // Zusatzaufgabe ändern

        for (int i = 0; i < 35; i++){
            pieces[i] = new Piece(this);
            pieces[i].setTag(i);

            if (i > 0){
                pieces[i].setOnClickListener(this);
            }else{
                pieces[i].setEnabled(false);
                pieces[i].setText("x");
                pieces[i].setTextColor(Color.RED);
            }

            grid.addView(pieces[i]);
        }
    }


    @Override
    public void onClick(View view){
        removePieces((int)view.getTag());
        updatePlayer();
        checkGameOver();

    }

    public void checkGameOver(){
        for (int i = 0; i < pieces.length; i++){
            if (pieces[i].isEnabled()){
                return;
            }
        }

        Intent i = new Intent(this, GameOverActivity.class);
        i.putExtra("looser", player);
        startActivity(i);
    }




    public void updatePlayer(){
        player = player ==1 ? 2 : 1;
        playerView.setText("Player " + player);
    }

    public void removePieces(int index){
        int reference = index % 5;
        for (int i = index; i < pieces.length; i++){
            if (pieces[i].isEnabled() && i%5 >= reference){
                removePiece(i);
            }
        }
    }

    public void removePiece(int index){
        pieces[index].setEnabled(false);
        ObjectAnimator oa = ObjectAnimator.ofFloat(pieces[index],View.ALPHA, 1,0);
        oa.setDuration(1500);
        oa.start();
    }
}
