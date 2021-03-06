package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class WallRight extends GameObstacle {

    private android.graphics.Paint Paint = new Paint();

    public WallRight(int minBallonX, int maxBallonX, int speed, int ballonWidth) {
        super(minBallonX, maxBallonX, speed);
        if(this.getObjectX()<minBallonX+(ballonWidth*1.5)){
            this.fixPosition((int)(minBallonX+(ballonWidth*2.5)));
        }
        else if(this.getObjectX()>minBallonX+(maxBallonX-minBallonX)/2){
            this.fixPosition((int)(minBallonX+(ballonWidth*2.5)));
        }
        Paint.setColor(Color.BLUE);
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
