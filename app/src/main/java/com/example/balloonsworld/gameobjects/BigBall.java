package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BigBall extends GameObstacle {
    private Paint paint=new Paint();
    public BigBall(int minBallonX, int maxBallonX, int level) {
        super(minBallonX, maxBallonX, level);
        this.fixPosition(minBallonX+(maxBallonX-minBallonX)/2);
        paint.setColor(Color.RED);
        paint.setAntiAlias(false);
    }
    public void drawNow(Canvas canvas) {
        canvas.drawCircle(this.getObjectX(),this.getObjectY(),(this.getMaxBallonX()-this.getMinBallonX())/2,paint);

    }
    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return(canvasHeight - ballon.getHeight()*2<this.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>this.getObjectY()+(this.getMaxBallonX()-this.getMinBallonX()) &&
                this.getObjectX()-(this.getMaxBallonX()-this.getMinBallonX())/2<balloonX&&
                this.getObjectX()-(this.getMaxBallonX()+this.getMinBallonX())/2<ballon.getWidth()+balloonX);
    }
}
