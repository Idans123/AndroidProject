package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class WallBlock extends GameObstacle {

    private android.graphics.Paint Paint = new Paint();
    private int xSpeed=10;


    public WallBlock(int minBallonX, int maxBallonX, int speed, ObjectType type) {
        super(minBallonX, maxBallonX, speed, type);
        Paint.setColor(Color.BLACK);
        Paint.setAntiAlias(false);
    }
    public  void drawNow(Canvas canvas){
//        canvas.drawRect(this.getObjectX(),this.getObjectY(),canvasWidth,this.getObjectY()+obstacle.getRadius(),obstacle.getPaint());
        canvas.drawRect(this.getObjectX(),this.getObjectY(),this.getObjectX()+300,this.getObjectY()+50,Paint);
    }
    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return(canvasHeight - ballon.getHeight()*2<this.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>this.getObjectY() &&
                this.getObjectX()<balloonX && this.getObjectX()+300>ballon.getWidth()+balloonX);

    }

    @Override
    public void update() {
        super.update();
        if(getObjectX()+300>this.getMaxBallonX()){
            xSpeed*=-1;
        }
        else if(getObjectX()<this.getMinBallonX()){
            xSpeed*=-1;
        }
        this.setObstacleX(xSpeed);


    }
}
