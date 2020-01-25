package com.example.balloonsworld.gameobjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Shield extends NewGameConsumable {
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
}
