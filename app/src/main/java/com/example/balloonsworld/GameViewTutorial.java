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
import android.view.View;

import com.example.balloonsworld.gameobjects.ConsumablesFactory;
import com.example.balloonsworld.gameobjects.GameConsumable;
import com.example.balloonsworld.gameobjects.ObstaclesFactory;
import com.example.balloonsworld.gameobjects.TutorialManager;

public class GameViewTutorial extends View implements SensorEventListener {

    private GameViewTutorial.GameEventListener listener;



    interface GameEventListener{
        void pauseGame();
        void endGame();
    }
    public void setListner(GameViewTutorial.GameEventListener listener){
        this.listener=listener;
    }

    SensorManager manager;
    Sensor sensor;

    private Bitmap ballon;
    private float speed=0;
    private float acc=0;

    //Background-params
    private Bitmap background;
    private Rect rect;
    int dWidth, dHeight;

    private int canvasHeight;
    private int canvasWidth;
    private int balloonX;

    private Bitmap[] ballonLife=new Bitmap[3];
    int lifes=3;
    private Bitmap pause;

    private int score=0;
    private Paint scorePaint=new Paint();



    private Paint shieldPaint=new Paint();

    private TutorialManager tutorialManager;
    private int tutorialStage=0;
    private boolean nextTutorial=true;

    private boolean touchRight=true;
    private boolean touchLeft=true;


    private GameConsumable coin=null;

    private ConsumablesFactory consumablesFactory;
    private ObstaclesFactory obstaclesFactory;



    public GameViewTutorial(Context context,SensorManager manager){
        super(context);

        tutorialManager=new TutorialManager(context);

        consumablesFactory = new ConsumablesFactory(context);
        obstaclesFactory=new ObstaclesFactory(context);

        this.manager=manager;
        sensor=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor!=null){
            this.manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);
        }
        initBackground();
        initBitmaps();
        initPaints();
        balloonX=canvasWidth/2 - ballon.getWidth()/2;
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        //drawing our static objects: PAUSE, LIFES and SCORE
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        canvas.drawBitmap(background,null,rect,null);
        drawLifes(canvas);
        canvas.drawBitmap(this.pause,20,20,null);
        canvas.drawText("score: "+score,150,60,scorePaint); //TO DO - Localize "Score" to hebrew

        //update ballon position
        int minBallonX=ballon.getWidth();
        int maxBallonX = (int)(canvasWidth - (ballon.getWidth()*1.5));

        if(nextTutorial){
                this.tutorialManager.update();
                if(this.tutorialManager.getObjectY()>canvasHeight+200&&
                        this.touchRight&&this.touchLeft&&
                this.coin==null){
                    this.tutorialStage=this.tutorialManager.nextTutorial();
                    if(this.tutorialStage==3){
                        this.touchRight=false;
                    }
                    else if(this.tutorialStage==4){
                        this.touchLeft=false;
                    }
                    else if(this.tutorialStage==5){
                        this.coin=consumablesFactory.generateConsumableForTutorial(minBallonX,maxBallonX);
                    }
                    else if(this.tutorialStage==6){
                        listener.endGame();
                    }

                }
                else{
                    this.tutorialManager.drawNow(canvas);
                }

            }



        balloonX+=(int)speed;

        if(balloonX<minBallonX){
            this.touchLeft=true;
            balloonX=minBallonX;
            speed=0;
            acc=0;
        }
        if(balloonX >maxBallonX){
            this.touchRight=true;
            balloonX=maxBallonX;
            speed=0;
            acc=0;
        }

        if(this.coin!=null){
            this.coin.update();
            if(coin.hitCheker(canvasHeight,ballon,balloonX)){
                int objectValue=coin.getValue();
                score+=coin.getValue();
                this.coin=null;
            }
            else{
                this.coin.drawNow(canvas);
                if(coin.getObjectY()>canvasHeight+100){
                    this.coin=consumablesFactory.generateConsumableForTutorial(minBallonX,maxBallonX);
                }
            }

        }



        canvas.drawBitmap(ballon,balloonX,canvasHeight - ballon.getHeight()*2,null);

    }
    private void drawLifes(Canvas canvas) {
        for(int i=lifes-1;i>=0;i--){
            int lifeX=(int) (canvasWidth-ballonLife[i].getWidth()-ballonLife[i].getWidth()*1.5*i);
            canvas.drawBitmap(ballonLife[i],lifeX,30,null);
        }
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

    private void initBitmaps(){
        ballon = BitmapFactory.decodeResource(getResources(),R.drawable.balloon);
        for(int i=0;i<lifes;i++){
            Bitmap life=BitmapFactory.decodeResource(getResources(),R.drawable.heart);
            ballonLife[i]=life;
        }
        this.pause=BitmapFactory.decodeResource(getResources(),R.drawable.pause);
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
}
