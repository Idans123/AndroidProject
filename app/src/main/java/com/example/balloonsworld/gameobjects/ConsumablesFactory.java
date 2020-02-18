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
        int randRes;
        if(level<=5){
            randRes= rand.nextInt(10);
        }
        else if(level<=10){
            randRes= rand.nextInt(15);
        }
        else if(level<=15){
            randRes= rand.nextInt(18);
        }
        else{
            randRes= rand.nextInt(20);
        }

        if(randRes<=10){
            ArrayList<Bitmap> arr = getBronzeBitmapArr();
            return new BronzeCoin(minBallonX,maxBallonX,arr,level);
        }
        else if(randRes<=15){
            ArrayList<Bitmap> arr = getSilverBitmapArr();
            return new SilverCoin(minBallonX,maxBallonX,arr,level);

        }
        else if (randRes<=18){

            return new Shield(minBallonX,maxBallonX, BitmapFactory.decodeResource(context.getResources(),R.drawable.shield),level);

        }
        else{
            ArrayList<Bitmap> arr = getGoldBitmapArr();
            return new GoldCoin(minBallonX,maxBallonX,arr,level);
        }
    }

    public GameConsumable generateConsumableForTutorial(int minBallonX, int maxBallonX){
        ArrayList<Bitmap> arr = getBronzeBitmapArr();
        return new BronzeCoin(minBallonX,maxBallonX,arr,5);
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
