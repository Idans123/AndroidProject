package com.example.balloonsworld.gameobjects;

public class GameConsumable implements IGameObjects {
    private int consumableX, consumableY=0;
    private int minBallonX, maxBallonX;
    private int speed;
    private int type;
    private int value;

    public GameConsumable(int minBallonX, int maxBallonX, int speed, int type, int value) {
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
    public int getType() {
        return this.type;
    }
}
