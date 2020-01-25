package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GoldCoin extends GameConsumable {
    private Bitmap bitmapGoldCoin;
    public GoldCoin(int minBallonX, int maxBallonX, Bitmap bitmapGoldCoin) {
        super(minBallonX, maxBallonX, 20, 40);
        this.bitmapGoldCoin=bitmapGoldCoin;
    }

    @Override
    public void drawNow(Canvas canvas) {
        canvas.drawBitmap(bitmapGoldCoin,getObjectX(),getObjectY(),null);
    }
    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return(canvasHeight - ballon.getHeight()*2<this.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>this.getObjectY() &&
                this.getObjectX()>balloonX && this.getObjectX()<ballon.getWidth()+balloonX);
    }
}
