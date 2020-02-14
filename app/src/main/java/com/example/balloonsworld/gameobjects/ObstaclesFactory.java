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
        int randRes= rand.nextInt(20);



        if(randRes<=4){
            return new Wall(minBallonX,maxBallonX,level,ballonWidth);
        }
        else if(randRes<=9){
            return new WallLeft(minBallonX,maxBallonX,level,ballonWidth);
        }
        else if(randRes<=14){
            return new WallWithGap(minBallonX,maxBallonX,level,ballonWidth);
        }
        else if(randRes<=0){
            return new BigBall(minBallonX,maxBallonX,level);
        }
        else{
            return new WallZigZag(minBallonX,maxBallonX,level);
        }
    }
    public GameObstacle generateObstacleForTutorial(int minBallonX, int maxBallonX,int ballonWidth){
        return new WallWithGap(minBallonX,maxBallonX,5,ballonWidth);
    }

}
