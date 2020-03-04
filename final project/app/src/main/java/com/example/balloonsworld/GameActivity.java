package com.example.balloonsworld;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import static android.widget.Toast.LENGTH_LONG;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private GameViewTutorial gameViewTutorial;
    private Handler  handler= new Handler();
    private final static long interval=30;
    private Timer timer;
    private AlertDialog menuDialog;
    private String currentUserName;
    private SharedPreferences sharedPreferences;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        sharedPreferences = getSharedPreferences("storage",MODE_PRIVATE);
        getSupportActionBar().hide();
        currentUserName = getIntent().getStringExtra("player_name");
        this.level = getIntent().getIntExtra("level",0);
        if(this.level==0){
            initGameViewTutorial();
            setContentView(gameViewTutorial);
        }
        else{
            this.initGameView();
            setContentView(gameView);
        }
        this.timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(level==0){
                            gameViewTutorial.invalidate();
                        }
                        else{
                            gameView.invalidate();
                        }
                    }
                });
            }
        },0,interval);
    }
    public void initGameViewTutorial(){
        gameViewTutorial=new GameViewTutorial(this,(SensorManager)getSystemService(SENSOR_SERVICE));
        gameViewTutorial.setListner(new GameViewTutorial.GameEventListener(){
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
                        initGameViewTutorial();
                        setContentView(gameViewTutorial);
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
                menuDialog.setCancelable(false);
                menuDialog.setCanceledOnTouchOutside(false);
            }
            @Override
            public void endGame() {
                timer.cancel();
                final AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                View endTutorialDialog=getLayoutInflater().inflate(R.layout.turtorial__end,null);
                final Button tryAgainBtn=endTutorialDialog.findViewById(R.id.tryAgainBtn);
                final Button returnToMenuBtn=endTutorialDialog.findViewById(R.id.returnToMenuBtn);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int userLevel=sharedPreferences.getInt("level",0);
                if(userLevel==0){
                    editor.putInt("level",1);
                    editor.commit();
                }

                tryAgainBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initGameViewTutorial();
                        setContentView(gameViewTutorial);
                        resumeGame();
                        menuDialog.dismiss();

                    }
                });

                returnToMenuBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setResult(RESULT_OK,null);
                        finish();

                    }
                });

                menuDialog= builder.setView(endTutorialDialog).show();
                menuDialog.setCancelable(false);
                menuDialog.setCanceledOnTouchOutside(false);
            }
            public void exitGame(){
                setResult(RESULT_OK,null);
                finish();
            }
            public void restartGame(){

            }
            public void resumeGame() {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                gameViewTutorial.invalidate();
                            }
                        });
                    }
                },0,interval);
            }
        });
    }
    public void initGameView(){
        gameView = new GameView(this,this.level,(SensorManager)getSystemService(SENSOR_SERVICE));
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
                menuDialog.setCancelable(false);
                menuDialog.setCanceledOnTouchOutside(false);
            }

            @Override
            public void endGame(int score,int level) {
                timer.cancel();
                final AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int userLevel=sharedPreferences.getInt("level",0);
                if(userLevel<level){
                    editor.putInt("level",level);
                    editor.commit();
                }
                View endGameDialog=getLayoutInflater().inflate(R.layout.end_game_menu,null);
                final TextView endGameHighScoreTv=endGameDialog.findViewById(R.id.endGameHighScoreTv);
                final TextView userScoreTV=endGameDialog.findViewById(R.id.userScoreTV);
                final Button tryAgainBtn=endGameDialog.findViewById(R.id.tryAgainBtn);
                final Button returnToMenuBtn=endGameDialog.findViewById(R.id.returnToMenuBtn);

                int newHighScore=isInTop10(score);
                if(newHighScore!=0){
                    endGameHighScoreTv.setText(getResources().getString(R.string.new_high_score)+"\n"+getResources().getString(R.string.entered_to_leadboard) +" "+newHighScore);
                }
                userScoreTV.setText(getResources().getString(R.string.your_score_is)+" "+score);
                tryAgainBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initGameView();
                        setContentView(gameView);
                        resumeGame();
                        menuDialog.dismiss();
                    }
                });

                returnToMenuBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitGame();
                    }
                });

                menuDialog= builder.setView(endGameDialog).show();
            }
            public void exitGame(){
                setResult(RESULT_OK,null);
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
    private int isInTop10(int score) {
        ArrayList<Integer> highScores = new ArrayList<Integer>();
        ArrayList<String> userNames = new ArrayList<String>();
        int indexInHighScore = 0;
        boolean scoreIsAddedDefualt=false;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 1; i <= 10; i++) {
            if (sharedPreferences.contains("player_name" + i) && sharedPreferences.contains("player_score" + i)) {
                int currScore = Integer.parseInt(sharedPreferences.getString("player_score" + i, ""));
                highScores.add(currScore);
                userNames.add(sharedPreferences.getString("player_name" + i,""));
                if (score > currScore&&indexInHighScore==0) {
                    indexInHighScore = i ;
                }
            }
        }
        if(highScores.size()==0){
            indexInHighScore=1;
        }
        if(highScores.size()<10&&score!=0&&indexInHighScore==0){
            highScores.add(score);
            userNames.add(currentUserName);
            scoreIsAddedDefualt=true;
        }
        if(indexInHighScore!=0){
            highScores.add(indexInHighScore-1,score);
            userNames.add(indexInHighScore-1,currentUserName);
            for(int i=1;i<=highScores.size();i++){
                editor.putString("player_name" + i,userNames.get(i-1));
                editor.putString("player_score" + i,highScores.get(i-1)+"");
            }
            editor.commit();
        }
        if(scoreIsAddedDefualt){
            indexInHighScore=highScores.size();
        }
        return indexInHighScore;
    }
    @Override
    public void onBackPressed() {
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
                if(level==0){
                    initGameViewTutorial();
                    setContentView(gameViewTutorial);
                }
                else{
                    initGameView();
                    setContentView(gameView);
                }
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
        menuDialog.setCancelable(false);
        menuDialog.setCanceledOnTouchOutside(false);
    }
    public void exitGame(){
        setResult(RESULT_OK,null);
        finish();
    }
    public void restartGame(){

    }
    public void resumeGame() {
        timer = new Timer();
        if(level==0){
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            gameViewTutorial.invalidate();
                        }
                    });
                }
            },0,interval);
        }
        else{
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
}
