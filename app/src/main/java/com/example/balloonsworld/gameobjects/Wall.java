package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Wall extends GameObstacle {

    private android.graphics.Paint Paint = new Paint();

    public Wall(int minBallonX, int maxBallonX, int speed) {
        super(minBallonX, maxBallonX, speed);
        Paint.setColor(Color.BLACK);
        Paint.setAntiAlias(false);
    }

    public  void drawNow(Canvas canvas){

        canvas.drawRect(this.getObjectX(),this.getObjectY(),canvas.getWidth(),this.getObjectY()+50,Paint);
    }
    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return(canvasHeight - ballon.getHeight()*2<this.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>this.getObjectY()+50 &&
                this.getObjectX()<balloonX );
    }
}
