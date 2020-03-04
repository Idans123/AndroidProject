package com.example.balloonsworld;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import com.example.balloonsworld.gameobjects.ConsumablesFactory;
import com.example.balloonsworld.gameobjects.GameObstacle;
import com.example.balloonsworld.gameobjects.GameConsumable;
import com.example.balloonsworld.gameobjects.LevelManager;
import com.example.balloonsworld.gameobjects.ObstaclesFactory;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class GameView extends View implements SensorEventListener {
    private GameEventListener listener;

    interface GameEventListener{
        void pauseGame();
        void resumeGame();
        void endGame(int score,int level);
    }

    public void setListner(GameEventListener listener){
        this.listener=listener;
    }
    SensorManager manager;
    Sensor sensor;
    private int level;
    private LevelManager levelObj;
    private boolean showingLevel=true;
    private Bitmap ballon;
    private float speed=0;
    private float acc=0;
    //Background-params
    private Bitmap background;
    private Rect rect;
    int dWidth, dHeight;
    //Background-params
    private int canvasHeight;
    private int canvasWidth;
    private int balloonX;
    private boolean dropItemType=true;//false for obstacle, true for consumable
    private boolean timeToDropAnother=true;
    private ArrayList<GameConsumable> currentConsumeableArr = new ArrayList<>();
    private ArrayList<GameConsumable> consumablesToRemove = new ArrayList<>();
    private ArrayList<GameObstacle> obstaclesArr=new ArrayList<GameObstacle>();
    private ArrayList<GameObstacle> obstaclesToRemove=new ArrayList<GameObstacle>();
    private Bitmap[] ballonLife=new Bitmap[3];
    int lifes=3;
    private int score=0;
    private Paint scorePaint=new Paint();
    private boolean shield=false;
    private Paint shieldPaint=new Paint();
    private Bitmap pause;
    private ConsumablesFactory consumablesFactory;
    private ObstaclesFactory obstaclesFactory;

    public GameView(Context context,int level,SensorManager manager) {
        super(context);
        this.manager=manager;
        sensor=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor!=null){
            this.manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);
        }
        initBackground();
        initBitmaps();
        initPaints();
        balloonX=canvasWidth/2 - ballon.getWidth()/2;
        consumablesFactory = new ConsumablesFactory(context);
        obstaclesFactory=new ObstaclesFactory(context);
        this.level=level;
        levelObj=new LevelManager(level,context);
    }
    private void initBackground() {
        this.background = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        Display display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        this.dWidth = size.x;
        this.dHeight = size.y;
        this.rect = new Rect(0,0,dWidth,dHeight);
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
        scorePaint.setTextSize(25*getResources().getDisplayMetrics().scaledDensity);
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

        //drawing our static objects: PAUSE, LIFES and SCORE
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        canvas.drawBitmap(background,null,rect,null);
        drawLifes(canvas);
        canvas.drawBitmap(this.pause,20,20,null);
        canvas.drawText(getResources().getString(R.string.score)+score,180,80,scorePaint);
        if(lifes==0){
            listener.endGame(this.score,this.level);
        }

        //update ballon position
        int minBallonX=0;
        int maxBallonX = (int)(canvasWidth - (ballon.getWidth()));

        if(showingLevel){
            if(currentConsumeableArr.size()==0&&obstaclesArr.size()==0){
                this.levelObj.update();
                if(this.levelObj.getObjectY()>canvasHeight+200){
                    showingLevel=!showingLevel;
                }
                else{
                    this.levelObj.drawNow(canvas);
                }
            }
        }
        else{
            if(!dropItemType&&timeToDropAnother){
                genarateConsumable(minBallonX,maxBallonX+(ballon.getWidth()/2));
            }
            else if(timeToDropAnother){
                genarateObstacle(minBallonX,maxBallonX+ballon.getWidth());
            }
        }
        balloonX+=(int)speed;
        if(balloonX<minBallonX){
            balloonX=minBallonX;
            speed=0;
            acc=0;
        }
        if(balloonX >maxBallonX){
            balloonX=maxBallonX;
            speed=0;
            acc=0;
        }

        if(shield){
            drawShield(canvas);
        }

        canvas.drawBitmap(ballon,balloonX,canvasHeight - ballon.getHeight()*2,null);

        //update all coins position
        for (GameConsumable consumable : currentConsumeableArr){
            consumable.update();
            if(consumable.hitCheker(canvasHeight,ballon,balloonX))
            {
                int objectValue=consumable.getValue();
                if(objectValue>=0){
                    score+=consumable.getValue();
                }
                else{
                    activateShield();
                }
                consumablesToRemove.add(consumable);
            }
            else if(consumable.getObjectY()>canvasHeight){
                consumablesToRemove.add(consumable);
            }
            else{
                consumable.drawNow(canvas);
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
        if(!showingLevel&&levelObj.progressManager(this.score)){
            this.level=levelObj.getLevel();
            showingLevel=!showingLevel;
        }
    }

    private void drawLifes(Canvas canvas) {
        for(int i=lifes-1;i>=0;i--){
            int lifeX=(int) (canvasWidth-ballonLife[i].getWidth()-ballonLife[i].getWidth()*1.5*i);
            canvas.drawBitmap(ballonLife[i],lifeX,30,null);
        }
    }

    private void drawShield(Canvas canvas) {
        canvas.drawCircle((int)(balloonX+ballon.getWidth()/2),(int)(canvasHeight - ballon.getHeight()*2 + ballon.getHeight()/2),ballon.getHeight()/2+10,shieldPaint);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        acc=event.values[0];
        System.out.println(acc);
        speed-=acc/6;
        if(speed>20) speed=20;
        if(speed<-20) speed=-20;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(event.getX()<120&&event.getY()<120){
                    listener.pauseGame();
                }
        }
        return true;
    }

    //will genarate a game consumable
    public void genarateConsumable(int minBallonX,int maxBallonX){
        dropItemType=!dropItemType;
        timeToDropAnother=false;
        currentConsumeableArr.add(consumablesFactory.generateConsumable(minBallonX,maxBallonX,level));
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        timeToDropAnother=true;
                    }
                },
                2000-(level*100)
        );
    }

    //will genarate an obstacle
    public void genarateObstacle(int minBallonX,int maxBallonX){
        dropItemType=!dropItemType;
        timeToDropAnother=false;
        obstaclesArr.add(obstaclesFactory.generateObstacle(minBallonX,maxBallonX,level,ballon.getWidth()));
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        timeToDropAnother=true;
                    }
                },
                2000-(level*100)
        );
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
