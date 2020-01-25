package com.example.balloonsworld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {
    Random rand = new Random();
    private Bitmap ballon;
    private int canvasHeight;
    private int canvasWidth;
    private int balloonX;
    private boolean dropItemType=true;//false for obstacle, true for consumable
    private boolean timeToDropAnother=true;
//    private int coinX, coinY=0, coinSpeed=16;
//    private Paint coinPaint = new Paint();
    private ArrayList<GameConsumable> coinsArr=new  ArrayList<GameConsumable>();
    private ArrayList<GameConsumable> coinsToRemove=new  ArrayList<GameConsumable>();


    private  ArrayList<GameObstacle> obstaclesArr=new ArrayList<GameObstacle>();
    private  ArrayList<GameObstacle> obstaclesToRemove=new ArrayList<GameObstacle>();

    private Bitmap[] ballonLife=new Bitmap[3];
    int lifes=3;

    private int score=0;
    private Paint scorePaint=new Paint();

    private boolean touch = false;
    private boolean shield=false;

    private Paint shieldPaint=new Paint();


    public GameView(Context context) {
        super(context);
        initBitmaps();
        initPaints();
        balloonX=canvasWidth/2 - ballon.getWidth()/2;

    }
    private void initBitmaps(){
        ballon = BitmapFactory.decodeResource(getResources(),R.drawable.balloon);
        for(int i=0;i<lifes;i++){
            Bitmap life=BitmapFactory.decodeResource(getResources(),R.drawable.heart);
            ballonLife[i]=life;
        }
    }
    private void initPaints()
    {
        scorePaint.setColor(Color.RED);
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
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        for(int i=lifes-1;i>=0;i--){
            int lifeX=(int) (canvasWidth-ballonLife[i].getWidth()-ballonLife[i].getWidth()*1.5*i);
            canvas.drawBitmap(ballonLife[i],lifeX,30,null);
        }

        canvas.drawText("score: "+score,20,60,scorePaint);


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
        for (GameConsumable coin : coinsArr){
            coin.update();
            if(hitCheker(coin))
            {
                int objectValue=coin.getValue();
                if(objectValue>=0){
                    score+=coin.getValue();
                }
                else{
                    activateShield();
                }

                coinsToRemove.add(coin);
                System.out.println("Score: "+score);
            }
            else if(coin.getObjectY()>canvasHeight){
                coinsToRemove.add(coin);
            }
            canvas.drawCircle(coin.getObjectX(),coin.getObjectY(),coin.getRadius(),coin.getPaint());
        }
        coinsArr.removeAll(coinsToRemove);

        for (GameObstacle obstacle :obstaclesArr){
            obstacle.update();
            if(hitCheker(obstacle)&&!shield)
            {
                lifes--;
                obstaclesToRemove.add(obstacle);
                //end game, player hit obstacle
            }
            else if(obstacle.getObjectY()>canvasHeight){
                obstaclesToRemove.add(obstacle);
            }
            if(obstacle.getValue()=="Wall"){
                canvas.drawRect(obstacle.getObjectX(),obstacle.getObjectY(),canvasWidth,obstacle.getObjectY()+obstacle.getRadius(),obstacle.getPaint());
            }
        }
        obstaclesArr.removeAll(obstaclesToRemove);


    }
    public boolean hitCheker(int x, int y){
        return(canvasHeight - ballon.getHeight()*2<y &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>y &&
                x>ballon.getWidth() && x<ballon.getWidth()+balloonX);
    }
    //will check if an object of the game has hitted the ballon
    public boolean hitCheker(GameObjects GameObject){
        return(canvasHeight - ballon.getHeight()*2<GameObject.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>GameObject.getObjectY() &&
                GameObject.getObjectX()>balloonX && GameObject.getObjectX()<ballon.getWidth()+balloonX);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                balloonX=(int)event.getX();
//                System.out.println(event.getX()+","+event.getY());
        }
        return true;
    }

    //will genarate a game consumable
    public void genarateConsumable(int minBallonX,int maxBallonX){

        int randRes= rand.nextInt(10);


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
        if(randRes<=5){
            coinsArr.add(new YellowCoin(minBallonX,maxBallonX));

        }
        else if(randRes>5&&randRes<7){
            coinsArr.add(new GreenCoin(minBallonX,maxBallonX));

        }
        else{
            coinsArr.add(new BlueCoin(minBallonX,maxBallonX));

        }

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
        obstaclesArr.add(new WallObstacle(minBallonX,maxBallonX));

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
