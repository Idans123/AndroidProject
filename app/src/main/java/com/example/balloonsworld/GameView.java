package com.example.balloonsworld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.balloonsworld.gameobjects.ConsumablesFactory;
import com.example.balloonsworld.gameobjects.GameObstacle;
import com.example.balloonsworld.gameobjects.GameConsumable;
import com.example.balloonsworld.gameobjects.ObstaclesFactory;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {

    private GameEventListener listener;

    interface GameEventListener{
        void pauseGame();
        void resumeGame();
    }
    public void setListner(GameEventListener listener){
        this.listener=listener;
    }

    Random rand = new Random();
    private Bitmap ballon;
    private int canvasHeight;
    private int canvasWidth;
    private int balloonX;
    private boolean dropItemType=true;//false for obstacle, true for consumable
    private boolean timeToDropAnother=true;
    private ArrayList<GameConsumable> currentConsumeableArr = new ArrayList<>();
    private ArrayList<GameConsumable> consumablesToRemove = new ArrayList<>();


    private  ArrayList<GameObstacle> obstaclesArr=new ArrayList<GameObstacle>();
    private  ArrayList<GameObstacle> obstaclesToRemove=new ArrayList<GameObstacle>();

    private Bitmap[] ballonLife=new Bitmap[3];
    int lifes=3;

    private int score=0;
    private Paint scorePaint=new Paint();

    private boolean touch = false;
    private boolean shield=false;

    private Paint shieldPaint=new Paint();

            private Bitmap pause;

    private ConsumablesFactory consumablesFactory;
    private ObstaclesFactory obstaclesFactory;
    public GameView(Context context) {
        super(context);
        initBitmaps();
        initPaints();
        balloonX=canvasWidth/2 - ballon.getWidth()/2;
        consumablesFactory = new ConsumablesFactory(context);
        obstaclesFactory=new ObstaclesFactory(context);



    }
    private void initBitmaps(){
        ballon = BitmapFactory.decodeResource(getResources(),R.drawable.balloon);
        for(int i=0;i<lifes;i++){
            Bitmap life=BitmapFactory.decodeResource(getResources(),R.drawable.heart);
            ballonLife[i]=life;
        }
        this.pause=BitmapFactory.decodeResource(getResources(),R.drawable.pause);

    }
    private void initPaints()
    {
        scorePaint.setColor(Color.YELLOW);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);
        shieldPaint.setColor(Color.BLUE);
        shieldPaint.setAntiAlias(false);
        shieldPaint.setStrokeWidth(10);
        shieldPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        canvas.drawColor(Color.RED);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        canvas.drawColor(Color.RED);
        for(int i=lifes-1;i>=0;i--){
            int lifeX=(int) (canvasWidth-ballonLife[i].getWidth()-ballonLife[i].getWidth()*1.5*i);
            canvas.drawBitmap(ballonLife[i],lifeX,30,null);
        }
        
        canvas.drawBitmap(this.pause,20,20,null);
        canvas.drawText("score: "+score,150,60,scorePaint);


        //update ballon position
        int minBallonX=ballon.getWidth();
        int maxBallonX = (int)(canvasWidth - (ballon.getWidth()*1.5));

        if(!dropItemType&&timeToDropAnother){
            genarateConsumable(minBallonX,maxBallonX);

        }
        else if(timeToDropAnother){

            genarateObstacle(minBallonX,maxBallonX);
        }

        if(balloonX<minBallonX){
            balloonX=minBallonX;
        }
        if(balloonX >maxBallonX){
            balloonX=maxBallonX;
        }
        if(shield){
            canvas.drawCircle((int)(balloonX+ballon.getWidth()/2),(int)(canvasHeight - ballon.getHeight()*2 + ballon.getHeight()/2),ballon.getHeight()/2+10,shieldPaint);
        }
        canvas.drawBitmap(ballon,balloonX,canvasHeight - ballon.getHeight()*2,null);

//        coinY+=coinSpeed;
        //update all coins position
        for (GameConsumable coin : currentConsumeableArr){
            coin.update();
            if(coin.hitCheker(canvasHeight,ballon,balloonX))
            {
                int objectValue=coin.getValue();
                if(objectValue>=0){
                    score+=coin.getValue();
                }
                else{
                    activateShield();
                }

                consumablesToRemove.add(coin);
                System.out.println("Score: "+score);
            }
            else if(coin.getObjectY()>canvasHeight){
                consumablesToRemove.add(coin);
            }
            else{
                coin.drawNow(canvas);
            }

        }
        currentConsumeableArr.removeAll(consumablesToRemove);

        for (GameObstacle obstacle :obstaclesArr){
            obstacle.update();
            if(obstacle.hitCheker(canvasHeight,ballon,balloonX)&&!shield)
            {
                lifes--;
                obstaclesToRemove.add(obstacle);
                //end game, player hit obstacle
            }
            else if(obstacle.getObjectY()>canvasHeight){
                obstaclesToRemove.add(obstacle);
            }
            else{
                obstacle.drawNow(canvas);
            }


        }
        obstaclesArr.removeAll(obstaclesToRemove);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                System.out.println(event.getX());
                System.out.println(event.getY());
                if(event.getX()<120&&event.getY()<120){
                    listener.pauseGame();
                }
            case MotionEvent.ACTION_MOVE:
                balloonX=(int)event.getX();
//                System.out.println(event.getX()+","+event.getY());
        }
        return true;
    }

    //will genarate a game consumable
    public void genarateConsumable(int minBallonX,int maxBallonX){
        dropItemType=!dropItemType;
        timeToDropAnother=false;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        timeToDropAnother=true;
                    }
                },
                500
        );
        currentConsumeableArr.add(consumablesFactory.generateConsumable(minBallonX,maxBallonX,2));
    }

    //will genarate an obstacle
    public void genarateObstacle(int minBallonX,int maxBallonX){
        dropItemType=!dropItemType;
        timeToDropAnother=false;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        timeToDropAnother=true;
                    }
                },
                500
        );
        obstaclesArr.add(obstaclesFactory.generateObstacle(minBallonX,maxBallonX,2,ballon.getWidth()));

    }
    public void activateShield(){
        shield=true;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        shield=false;
                    }
                },
                5000
        );
    }

}
