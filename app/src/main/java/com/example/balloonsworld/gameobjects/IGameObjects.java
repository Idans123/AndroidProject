package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public interface IGameObjects {
    int getObjectY();
    int getObjectX();
    void update();
    void drawNow(Canvas canvas);
    boolean hitCheker(int canvasHeight, Bitmap ballon,int balloonX);
    ObjectType getType();
}
