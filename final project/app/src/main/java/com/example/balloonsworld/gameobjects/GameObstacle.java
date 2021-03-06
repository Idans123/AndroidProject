package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class GameObstacle implements IGameObjects {
    private int obstacleX, obstacleY=0;
    private int minBallonX, maxBallonX;
    private int speed;

    public GameObstacle(int minBallonX, int maxBallonX, int speed) {
        this.obstacleX = (int)Math.floor(Math.random()*(maxBallonX-minBallonX))+minBallonX;
        this.obstacleY = 0;
        this.minBallonX = minBallonX;
        this.maxBallonX = maxBallonX;
        this.speed = speed+5;
    }

    public int getMinBallonX(){
        return minBallonX;
    }
    public int getMaxBallonX(){
        return maxBallonX;
    }

    @Override
    public int getObjectY() {
        return this.obstacleY;
    }

    @Override
    public int getObjectX() {
        return this.obstacleX;
    }

    public void setObstacleX(int speed){this.obstacleX+=speed;}

    @Override
    public void update() {
        this.obstacleY+=speed;
    }

    public void fixPosition(int x){
        this.obstacleX=x;
    }

    @Override
    public abstract void drawNow(Canvas canvas);
    public abstract boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX);

}


