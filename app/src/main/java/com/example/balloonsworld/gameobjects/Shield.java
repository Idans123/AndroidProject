package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Shield extends GameConsumable {
    private Bitmap bitmapShield;

    public Shield(int minBallonX, int maxBallonX, Bitmap bitmapShield) {
        super(minBallonX, maxBallonX, 15, -1);
        this.bitmapShield = bitmapShield;
    }

    @Override
    public void drawNow(Canvas canvas) {
        canvas.drawBitmap(bitmapShield,getObjectX(),getObjectY(),null);
    }

    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return(canvasHeight - ballon.getHeight()*2<this.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>this.getObjectY() &&
                this.getObjectX()>balloonX && this.getObjectX()<ballon.getWidth()+balloonX);
    }
}
