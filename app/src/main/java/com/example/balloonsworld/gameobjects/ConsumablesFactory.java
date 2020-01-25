package com.example.balloonsworld.gameobjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.graphics.drawable.IconCompat;

import com.example.balloonsworld.R;

import java.util.Random;

public class ConsumablesFactory {
    private int minBallonX, maxBallonX;
    private Context context;
    public ConsumablesFactory(int minBallonX, int maxBallonX, Context context) {
        this.minBallonX = minBallonX;
        this.maxBallonX = maxBallonX;
        this.context=context;
    }

    public GameConsumable generateConsumable(int level){
        Random rand = new Random();
        int randRes= rand.nextInt(10);

        if(randRes<=3){
            return new BronzeCoin(minBallonX,maxBallonX,BitmapFactory.decodeResource(context.getResources(), R.drawable.bronze));
        }
        else if(randRes>3&&randRes<5){
            return new SilverCoin(minBallonX,maxBallonX,BitmapFactory.decodeResource(context.getResources(), R.drawable.silver));
        }
        else if (randRes>5&&randRes<7){
            return new GoldCoin(minBallonX,maxBallonX,BitmapFactory.decodeResource(context.getResources(), R.drawable.gold));
        }
        else{
            return new Shield(minBallonX,maxBallonX);
        }
    }
}
