package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BronzeCoin extends GameConsumable {
    private Bitmap bitmapBronzeCoin;

    public BronzeCoin(int minBallonX, int maxBallonX,Bitmap bitmapBronzeCoin) {
        super(minBallonX, maxBallonX, 16, ObjectType.BITMAP, 10);
        this.bitmapBronzeCoin = bitmapBronzeCoin;
    }

    @Override
    public void drawNow(Canvas canvas) {
        canvas.drawBitmap(bitmapBronzeCoin,getObjectX(),getObjectY(),null);
    }
}
