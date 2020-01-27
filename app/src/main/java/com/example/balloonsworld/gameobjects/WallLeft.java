package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class WallLeft extends  GameObstacle {
    private android.graphics.Paint Paint = new Paint();

    public WallLeft(int minBallonX, int maxBallonX, int speed,int ballonWidth) {
        super(minBallonX, maxBallonX, speed);
        if(this.getObjectX()>maxBallonX-(ballonWidth*1.5)){
            this.fixPosition((int)(maxBallonX-(ballonWidth)));
            Paint.setColor(Color.GREEN);
        }
        else if(this.getObjectX()<minBallonX+(maxBallonX-minBallonX)/2){
            this.fixPosition((int)(maxBallonX-(ballonWidth)));
            Paint.setColor(Color.GREEN);
        }
        else{
            Paint.setColor(Color.BLACK);
        }
//        Paint.setColor(Color.BLACK);
        Paint.setAntiAlias(false);
    }

    public  void drawNow(Canvas canvas){

        canvas.drawRect(0,this.getObjectY(),this.getObjectX(),this.getObjectY()+50,Paint);
    }
    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return(canvasHeight - ballon.getHeight()*2<this.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>this.getObjectY()+50 &&
                this.getObjectX()>balloonX );
    }
}
