package com.example.balloonsworld.gameobjects;

public class GameObstacle implements IGameObjects {
    private int obstacleX, obstacleY=0;
    private int minBallonX, maxBallonX;
    private int speed;
    private ObjectType type;

    public GameObstacle(int minBallonX, int maxBallonX, int speed, ObjectType type) {
        this.obstacleX = (int)Math.floor(Math.random()*(maxBallonX-minBallonX))+minBallonX;
        this.obstacleY = 0;
        this.minBallonX = minBallonX;
        this.maxBallonX = maxBallonX;
        this.speed = speed;
        this.type = type;
    }

    @Override
    public int getObjectY() {
        return this.obstacleY;
    }

    @Override
    public int getObjectX() {
        return this.obstacleX;
    }

    @Override
    public void update() {
        this.obstacleY+=speed;
    }

    @Override
    public ObjectType getType() {
        return this.type;
    }
}
