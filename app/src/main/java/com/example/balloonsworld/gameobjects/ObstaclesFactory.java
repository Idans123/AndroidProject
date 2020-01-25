package com.example.balloonsworld.gameobjects;

import android.content.Context;

import java.util.Random;

public class ObstaclesFactory {
    private Context context;
    public ObstaclesFactory(Context context) {
        this.context=context;
    }

    public GameObstacle generateObstacle(int minBallonX, int maxBallonX, int level){
        Random rand = new Random();
        int randRes= rand.nextInt(10);

        if(randRes<=3){
            return new Wall(minBallonX,maxBallonX,15);
        }
        else{
            return new WallZigZag(minBallonX,maxBallonX,15);
        }
    }

}
