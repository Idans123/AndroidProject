package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class GoldCoin extends GameConsumable {
    private ArrayList<Bitmap> bitmapsGoldCoinArr;
    private int i=0;
    public GoldCoin(int minBallonX, int maxBallonX, ArrayList<Bitmap> bitmapGoldCoin) {
        super(minBallonX, maxBallonX, 15, 40);
        this.bitmapsGoldCoinArr=bitmapGoldCoin;
    }

    @Override
    public void drawNow(Canvas canvas) {
        canvas.drawBitmap(bitmapsGoldCoinArr.get(i/5),getObjectX(),getObjectY(),null);
        i++;
        i=i%25;    }
    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return(canvasHeight - ballon.getHeight()*2<this.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>this.getObjectY() &&
                this.getObjectX()>balloonX && this.getObjectX()<ballon.getWidth()+balloonX);
    }
}
