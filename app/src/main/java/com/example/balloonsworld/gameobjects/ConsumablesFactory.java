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
            ArrayList<Bitmap> arr = getBronzeBitmapArr();
            return new BronzeCoin(minBallonX,maxBallonX,arr);
        }
        else if(randRes>3&&randRes<5){
            ArrayList<Bitmap> arr = getSilverBitmapArr();
            return new SilverCoin(minBallonX,maxBallonX,arr);
        }
        else if (randRes>5&&randRes<7){
            ArrayList<Bitmap> arr = getGoldBitmapArr();
            return new GoldCoin(minBallonX,maxBallonX,arr);
        }
        else{
            return new Shield(minBallonX,maxBallonX, BitmapFactory.decodeResource(context.getResources(),R.drawable.shield));
        }
    }
    private ArrayList<Bitmap> getBronzeBitmapArr(){
        ArrayList<Bitmap> coins = new ArrayList<>();
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.bronze));
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.bronze2));
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.bronze3));
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.bronze4));
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.bronze5));
        return coins;
    }
    private ArrayList<Bitmap> getGoldBitmapArr(){
        ArrayList<Bitmap> coins = new ArrayList<>();
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.gold));
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.gold2));
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.gold3));
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.gold4));
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.gold5));
        return coins;
    }
    private ArrayList<Bitmap> getSilverBitmapArr(){
        ArrayList<Bitmap> coins = new ArrayList<>();
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.silver));
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.silver2));
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.silver3));
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.silver4));
        coins.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.silver5));
        return coins;
    }
}
