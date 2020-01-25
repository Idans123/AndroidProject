package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;

public class BronzeCoin extends NewGameConsumable {
    private Bitmap bitmapBronzeCoin;

    public BronzeCoin(int minBallonX, int maxBallonX,Bitmap bitmapBronzeCoin) {
        super(minBallonX, maxBallonX, 16, ObjectType.BITMAP, 10);
        bitmapBronzeCoin=bitmapBronzeCoin;
    }
}
