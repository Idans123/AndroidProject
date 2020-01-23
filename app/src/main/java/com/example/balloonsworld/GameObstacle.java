package com.example.balloonsworld;

import android.graphics.Paint;

public class GameObstacle implements  GameObjects {
    private int consumableX, consumableY=0, speed;
    private android.graphics.Paint Paint = new Paint();
    private int minBallonX;
    private int maxBallonX;
    private String value;
    private  int radius;
    private int color;

    public GameObstacle(int minBallonX,int maxBallonX,int speed,int radius,int color,String value){
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
    public String getValue(){
        return value;
    }
    public int getRadius(){
        return radius;
    }
    public int getColor(){
        return color;
    }
}


