package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class GameConsumable implements IGameObjects {
    private int consumableX, consumableY=0;
    private int minBallonX, maxBallonX;
    private int speed;
    private int value;

    public GameConsumable(int minBallonX, int maxBallonX, int speed, int value) {
        this.consumableX = (int)Math.floor(Math.random()*(maxBallonX-minBallonX))+minBallonX;
        this.consumableY = 0;
        this.minBallonX = minBallonX;
        this.maxBallonX = maxBallonX;
        this.speed = speed+5;
        this.value = value;
    }

    @Override
    public int getObjectY() {
        return this.consumableY;
    }

    @Override
    public int getObjectX() {
        return this.consumableX;
    }

    @Override
    public void update() {
        this.consumableY+=speed;
    }

    public int getValue() {
        return value;
    }
    public abstract void drawNow(Canvas canvas);
    public abstract boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX);
}

