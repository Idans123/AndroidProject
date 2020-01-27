package com.example.balloonsworld.gameobjects;

import android.content.Context;

import java.util.Random;

public class ObstaclesFactory {
    private Context context;
    public ObstaclesFactory(Context context) {
        this.context=context;
    }

    public GameObstacle generateObstacle(int minBallonX, int maxBallonX, int level,int ballonWidth){
        Random rand = new Random();
        int randRes= rand.nextInt(20);

        if(randRes<=4){
            return new Wall(minBallonX,maxBallonX,15,ballonWidth);
        }
        else if(randRes<=9){
            return new WallLeft(minBallonX,maxBallonX,15,ballonWidth);
        }
        else if(randRes<=14){
            return new WallWithGap(minBallonX,maxBallonX,15,ballonWidth);
        }
        else{
            return new WallZigZag(minBallonX,maxBallonX,15);
        }
    }

}
