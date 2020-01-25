package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;

public class SilverCoin extends GameConsumable {
    private Bitmap bitmapSilverCoin;
    public SilverCoin(int minBallonX, int maxBallonX, Bitmap bitmapSilverCoin) {
        super(minBallonX, maxBallonX, 18, ObjectType.BITMAP, 20);
        this.bitmapSilverCoin=bitmapSilverCoin;
    }
}
