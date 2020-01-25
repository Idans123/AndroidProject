package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Shield extends GameConsumable {
    public Paint paint = new Paint();
    public Shield(int minBallonX, int maxBallonX) {
        super(minBallonX, maxBallonX, 20, ObjectType.CANVAS, -1);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(false);
    }

    @Override
    public void drawNow(Canvas canvas) {
        canvas.drawCircle(getObjectX(),getObjectY(),35,paint);
    }

    public  boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX){
        return(canvasHeight - ballon.getHeight()*2<this.getObjectY() &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>this.getObjectY() &&
                this.getObjectX()>balloonX && this.getObjectX()<ballon.getWidth()+balloonX);
    }
}
