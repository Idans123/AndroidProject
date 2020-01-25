package com.example.balloonsworld.gameobjects;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.balloonsworld.R;

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
            return new Wall(minBallonX,maxBallonX,15, ObjectType.BITMAP);
        }
        else{
            return new WallBlock(minBallonX,maxBallonX,15, ObjectType.BITMAP);
        }
    }

}
