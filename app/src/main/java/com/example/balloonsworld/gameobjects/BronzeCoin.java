package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BronzeCoin extends GameConsumable {
    private Bitmap bitmapBronzeCoin;

    public BronzeCoin(int minBallonX, int maxBallonX,Bitmap bitmapBronzeCoin) {
        super(minBallonX, maxBallonX, 15, 10);
        this.bitmapBronzeCoin = bitmapBronzeCoin;
    }

    @Override
    public void drawNow(Canvas canvas) {
        canvas.drawBitmap(bitmapBronzeCoin,getObjectX(),getObjectY(),null);
    }

    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return(canvasHeight - ballon.getHeight()*2<this.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>this.getObjectY() &&
                this.getObjectX()>balloonX && this.getObjectX()<ballon.getWidth()+balloonX);
    }
}
