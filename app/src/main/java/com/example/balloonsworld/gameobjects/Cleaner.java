package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Cleaner extends GameConsumable {
    public Cleaner(int minBallonX, int maxBallonX, int speed, ObjectType type, int value) {
        super(minBallonX, maxBallonX, speed, type, value);
    }

    @Override
    public void drawNow(Canvas canvas) {
        
    }
    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return true;
    }
}
