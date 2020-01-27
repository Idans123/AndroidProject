package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class WallWithGap extends GameObstacle {
    private android.graphics.Paint Paint = new Paint();
    private int gapLeft;
    private int gapRight;

    public WallWithGap(int minBallonX, int maxBallonX, int speed,int ballonWidth) {
        super(minBallonX, maxBallonX, speed);
        this.gapLeft=(int)(ballonWidth*1.5);
        this.gapRight=ballonWidth;
        if(this.getObjectX()<minBallonX+this.gapLeft){
            this.fixPosition(minBallonX+this.gapLeft);
            Paint.setColor(Color.GREEN);
        }
        else if(this.getObjectX()>maxBallonX-this.gapRight){
            this.fixPosition(maxBallonX);
            Paint.setColor(Color.GREEN);
        }
        else{
            Paint.setColor(Color.BLACK);
        }

        Paint.setAntiAlias(false);
    }

    public  void drawNow(Canvas canvas){

        canvas.drawRect(0,this.getObjectY(),this.getObjectX()-gapLeft,this.getObjectY()+50,Paint);
        canvas.drawRect(this.getObjectX(),this.getObjectY(),canvas.getWidth(),this.getObjectY()+50,Paint);
    }
    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return(canvasHeight - ballon.getHeight()*2<this.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>this.getObjectY()+50 &&
                this.getObjectX()-this.gapLeft>balloonX )||(canvasHeight - ballon.getHeight()*2<this.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>this.getObjectY()+50 &&
                this.getObjectX()<balloonX );
    }
}
