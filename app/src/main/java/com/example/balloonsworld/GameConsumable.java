package com.example.balloonsworld;

import android.graphics.Color;
import android.graphics.Paint;

public abstract   class GameConsumable implements  GameObjects {

    private int consumableX, consumableY=0, speed;
    private Paint Paint = new Paint();
    private int minBallonX;
    private int maxBallonX;
    private int value;
    private  int radius;
    private int color;

    public GameConsumable(int minBallonX,int maxBallonX,int speed,int radius,int color,int value){
        consumableY=0;
        consumableX=(int)Math.floor(Math.random()*(maxBallonX-minBallonX))+minBallonX;
        this.minBallonX=minBallonX;
        this.maxBallonX=maxBallonX;
        this.speed=speed;
        this.value=value;
        this.radius=radius;
        this.color=color;
        this.value=value;
        Paint.setColor(color);
        Paint.setAntiAlias(false);
    }

    public Paint getPaint(){
        return Paint;
    }
    public int getObjectY(){
        return consumableY;

    }
    public int getObjectX(){
        return consumableX;

    }
    public void update(){
        consumableY+=speed;

    }
    public int getValue(){
        return value;
    }
    public int getRadius(){
        return radius;
    }
    public int getColor(){
        return color;
    }

}
