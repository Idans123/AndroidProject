package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class SilverCoin extends GameConsumable {
    private ArrayList<Bitmap> bitmapsSilverCoinArr;
    private int i=0;
    public SilverCoin(int minBallonX, int maxBallonX, ArrayList<Bitmap> bitmapSiverCoinArr,int level) {
        super(minBallonX, maxBallonX, level, 20);
        this.bitmapsSilverCoinArr=bitmapSiverCoinArr;
    }

    @Override
    public void drawNow(Canvas canvas) {
        canvas.drawBitmap(bitmapsSilverCoinArr.get(i/5),getObjectX(),getObjectY(),null);
        i++;
        i=i%25;
    }
    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return(canvasHeight - ballon.getHeight()*2<this.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>this.getObjectY() &&
                this.getObjectX()>balloonX && this.getObjectX()<ballon.getWidth()+balloonX);
    }



}
