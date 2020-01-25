package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class SilverCoin extends GameConsumable {
    private Bitmap bitmapSilverCoin;
    public SilverCoin(int minBallonX, int maxBallonX, Bitmap bitmapSiverCoin) {
        super(minBallonX, maxBallonX, 18, 20);
        this.bitmapSilverCoin=bitmapSilverCoin;
    }

    @Override
    public void drawNow(Canvas canvas) {
        canvas.drawBitmap(bitmapSilverCoin,getObjectX(),getObjectY(),null);
    }
    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return(canvasHeight - ballon.getHeight()*2<this.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>this.getObjectY() &&
                this.getObjectX()>balloonX && this.getObjectX()<ballon.getWidth()+balloonX);
    }



}
