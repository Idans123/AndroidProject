package com.example.balloonsworld;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private Handler  handler= new Handler();
    private final static long interval=30;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        gameView.setListner(new GameView.GameEventListener() {
            @Override
            public void pauseGame() {
                timer.cancel();
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

}
