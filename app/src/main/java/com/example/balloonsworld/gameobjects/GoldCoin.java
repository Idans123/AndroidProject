package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GoldCoin extends GameConsumable {
    private Bitmap bitmapGoldCoin;
    public GoldCoin(int minBallonX, int maxBallonX, Bitmap bitmapGoldCoin) {
        super(minBallonX, maxBallonX, 20, ObjectType.BITMAP, 40);
        this.bitmapGoldCoin=bitmapGoldCoin;
    }

    @Override
    public void drawNow(Canvas canvas) {
        canvas.drawBitmap(bitmapGoldCoin,getObjectX(),getObjectY(),null);
    }
}
