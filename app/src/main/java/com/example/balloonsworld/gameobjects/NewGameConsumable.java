package com.example.balloonsworld.gameobjects;

import android.graphics.Canvas;

public abstract class NewGameConsumable implements IGameObjects {
    private int consumableX, consumableY=0;
    private int minBallonX, maxBallonX;
    private int speed;
    private ObjectType type;
    private int value;

    public NewGameConsumable(int minBallonX, int maxBallonX, int speed, ObjectType type, int value) {
        this.consumableX = (int)Math.floor(Math.random()*(maxBallonX-minBallonX))+minBallonX;
        this.consumableY = 0;
        this.minBallonX = minBallonX;
        this.maxBallonX = maxBallonX;
        this.speed = speed;
        this.type = type;
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

    @Override
    public ObjectType getType() {
        return this.type;
    }

    public int getValue() {
        return value;
    }
    public abstract void drawNow(Canvas canvas);
}

