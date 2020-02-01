package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class BronzeCoin extends GameConsumable {
    private ArrayList<Bitmap> bitmapsBronzeCoinArr;
    private int i=0;
    public BronzeCoin(int minBallonX, int maxBallonX,ArrayList<Bitmap> bitmapBronzeCoin,int level) {
        super(minBallonX, maxBallonX, level, 10);
        this.bitmapsBronzeCoinArr = bitmapBronzeCoin;
    }

    @Override
    public void drawNow(Canvas canvas) {
            canvas.drawBitmap(bitmapsBronzeCoinArr.get(i/5),getObjectX(),getObjectY(),null);
            i++;
            i=i%25;
    }

    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return(canvasHeight - ballon.getHeight()*2<this.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>this.getObjectY() &&
                this.getObjectX()>balloonX && this.getObjectX()<ballon.getWidth()+balloonX);
    }
}
