package com.example.balloonsworld;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private Handler  handler= new Handler();
    private final static long interval=30;
    private Timer timer;
    private AlertDialog menuDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initGameView();
        setContentView(gameView);

        this.timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        },0,interval);
    }
    public void initGameView(){
        gameView = new GameView(this,1);
        gameView.setListner(new GameView.GameEventListener() {
            @Override
            public void pauseGame() {
                timer.cancel();
                final AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);

                View pasueDialog=getLayoutInflater().inflate(R.layout.pause_game_menu,null);

                final Button resumeBtn=pasueDialog.findViewById(R.id.resumeBtn);
                final Button restartBtn=pasueDialog.findViewById(R.id.restartGameBtn);
                final Button exitBtn=pasueDialog.findViewById(R.id.exitGameBtn);

                resumeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resumeGame();
                        menuDialog.dismiss();

                    }
                });

                restartBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initGameView();
                        setContentView(gameView);
                        resumeGame();
                        menuDialog.dismiss();
                    }
                });
                exitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitGame();
                    }
                });

                menuDialog= builder.setView(pasueDialog).show();
            }

            @Override
            public void endGame() {
                
            }

            public void exitGame(){
                finish();
            }

            public void restartGame(){

            }

            @Override
            public void resumeGame() {

                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                gameView.invalidate();
                            }
                        });
                    }
                },0,interval);
            }
        });
    }




    private class AlertDialogListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which == DialogInterface.BUTTON_POSITIVE){
                finish();
            }
        }
    }



}
