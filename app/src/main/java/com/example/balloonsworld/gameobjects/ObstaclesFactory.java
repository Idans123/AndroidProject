package com.example.balloonsworld.gameobjects;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Random;

public class ObstaclesFactory {
    private Context context;
    public ObstaclesFactory(Context context) {
        this.context=context;
    }

    public GameObstacle generateObstacle(int minBallonX, int maxBallonX, int level,int ballonWidth){
        Random rand = new Random();
        int randRes;
        if(level<=5){
            randRes= rand.nextInt(5);
        }
        else if(level<=10){
            randRes= rand.nextInt(15);
        }
        else{
            randRes= rand.nextInt(20);
        }



        if(randRes<=5){
            return new WallWithGap(minBallonX,maxBallonX,level,ballonWidth);
        }
        else if(randRes<=10){
            return new WallLeft(minBallonX,maxBallonX,level,ballonWidth);
        }
        else if(randRes<=15){
            return new Wall(minBallonX,maxBallonX,level,ballonWidth);
        }
        else{
            return new WallZigZag(minBallonX,maxBallonX,level);
        }
    }
    public GameObstacle generateObstacleForTutorial(int minBallonX, int maxBallonX,int ballonWidth){
        return new WallWithGap(minBallonX,maxBallonX,5,ballonWidth);
    }

}
