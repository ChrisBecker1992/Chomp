package com.example.kimilk.chomp;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class GameActivity1 extends AppCompatActivity implements View.OnClickListener {


    protected int player = 1;
    protected int mode;
    protected GridLayout grid;
    protected TextView playerView;
    protected Piece[] pieces = new Piece[35];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        getSupportActionBar().hide();


        mode = getIntent().getIntExtra("mode", 2);
        Log.i("GameActivity1", "mode = " + mode);

        playerView = (TextView) findViewById(R.id.player00); // Referenz auf TextView
        grid = (GridLayout) findViewById(R.id.grid00);

        grid.setColumnCount(5); // Zusatzaufgabe ändern

        for (int i = 0; i < 35; i++){
            pieces[i] = new Piece(this);
            pieces[i].setTag(i);

            if (i > 0){
                pieces[i].setOnClickListener(this);
            }else{
                pieces[i].setEnabled(false);
                pieces[i].setText("x");
                pieces[i].setTextColor(Color.BLUE);
            }

            grid.addView(pieces[i]);
        }
    }


    @Override
    public void onClick (View view){
        removePieces((int) view.getTag());
        updatePlayer();
        Log.i("Es handelt sich um",player+"");
        checkGameOver();
        comPlayer();
        updatePlayer();
        Log.i("Es handelt sich um",player+"");
        checkGameOver();
    }



    public void comPlayer(){

        //Spieler/Comuterspieler Zug ausgeben
        if(player==2){

            int rr = randomGenerator();
            Log.i("Zufallszahl",rr+ "");

            if(comCheck(rr)==true){
                removePieces(rr);

            }else if(comCheck(rr)==false){
                comPlayer();
            }
        }
    }

    // Tryout um Com Gegner verlieren zulassen :(
    public void comGameOver(){
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].isEnabled()==false){

                Intent h = new Intent (this, GameOverActivity.class); // Intents braucht man um auf andere activities zu kommen
                h.putExtra("looser", player);
                startActivity(h);

            }
            return;
        }

    }

    public void updatePlayer(){
        Log.i("Es handelt sich um",player+"");
        player = player ==1 ? 2 : 1;
        playerView.setText("Player " + player);
    }

    public int randomGenerator(){
        //Computer Zug
        Random random = new Random();

        //int r = random.nextInt(35);
        int r = random.nextInt(35 - 1 + 1) + 1;

        return r;
    }


    public boolean comCheck (int index) {
        int reference = index % 5;

        for (int i = index; i < pieces.length; i++) {
            if (pieces[i].isEnabled() &&
                    i % 5 >= reference) {
                return true;
//        if (){
//                    //(pieces[index].isEnabled()==true){
//                return true;
//            }else{
//                return false;
//            }
            }
        }
        return false;
    }

    public void checkGameOver(){
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].isEnabled()){
                return;
            }
        }

        Intent i = new Intent (this, GameOverActivity.class); // Intents braucht man um auf andere activities zu kommen
        i.putExtra("looser", player);
        startActivity(i);
    }

    //um View zu entfernen
    public void removePiece (int index){
        pieces[index].setEnabled(false);
        ObjectAnimator oa = ObjectAnimator.ofFloat(pieces[index],View.ALPHA,1,0); //Factory Methode
        oa.setDuration(1500);
        oa.setInterpolator(new BounceInterpolator()); //zum bouncen
        oa.start();
    }

    public void removePieces (int index){
        int reference = index % 5;
        for (int i = index; i < pieces.length; i++){
            if (pieces[i].isEnabled()&&
                    i%5 >= reference) {
                removePiece(i);
            }
        }
    }
}
