package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class SilverCoin extends GameConsumable {
    private Bitmap bitmapSilverCoin;
    public SilverCoin(int minBallonX, int maxBallonX, Bitmap bitmapSilverCoin) {
        super(minBallonX, maxBallonX, 18, ObjectType.BITMAP, 20);
        this.bitmapSilverCoin=bitmapSilverCoin;
    }

    @Override
    public void drawNow(Canvas canvas) {
        canvas.drawBitmap(bitmapSilverCoin,getObjectX(),getObjectY(),null);
    }
}
