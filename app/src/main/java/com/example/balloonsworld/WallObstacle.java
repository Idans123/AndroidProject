package com.example.balloonsworld;

import android.graphics.Color;

public class WallObstacle extends GameObstacle  {

    public WallObstacle(int minBallonX,int maxBallonX){
        super(minBallonX,maxBallonX,16,100, Color.BLACK,"Wall");

    }
}
