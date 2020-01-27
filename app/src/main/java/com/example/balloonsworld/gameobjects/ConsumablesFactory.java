package com.example.balloonsworld.gameobjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.balloonsworld.R;

import java.util.ArrayList;
import java.util.Random;

public class ConsumablesFactory {
    private Context context;
    public ConsumablesFactory(Context context) {
        this.context=context;
    }

    public GameConsumable generateConsumable(int minBallonX, int maxBallonX, int level){
        Random rand = new Random();
        int randRes= rand.nextInt(10);

        if(randRes<=3){
            ArrayList<Bitmap> coins = new ArrayList<>();
            coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.bronze));
            coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.bronze2));
            coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.bronze3));
            coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.bronze5));
            coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.bronze6));
//            return new BronzeCoin(minBallonX,maxBallonX,BitmapFactory.decodeResource(context.getResources(), R.drawable.bronze));
            return new BronzeCoin(minBallonX,maxBallonX,coins);
        }
        else if(randRes>3&&randRes<5){
            return new SilverCoin(minBallonX,maxBallonX,BitmapFactory.decodeResource(context.getResources(), R.drawable.silver));
        }
        else if (randRes>5&&randRes<7){
            return new GoldCoin(minBallonX,maxBallonX,BitmapFactory.decodeResource(context.getResources(), R.drawable.gold));
        }
        else{
            return new Shield(minBallonX,maxBallonX, BitmapFactory.decodeResource(context.getResources(),R.drawable.shield));
        }
    }
}
