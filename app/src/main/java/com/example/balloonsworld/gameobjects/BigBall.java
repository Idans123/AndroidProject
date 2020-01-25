package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BigBall extends GameObstacle {
    public BigBall(int minBallonX, int maxBallonX, int speed, ObjectType type) {
        super(minBallonX, maxBallonX, speed, type);
    }
    public void drawNow(Canvas canvas) {

    }
    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return true;
    }
}
