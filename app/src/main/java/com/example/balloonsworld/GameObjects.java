package com.example.balloonsworld;

import android.graphics.Paint;

public interface GameObjects {
    public Paint getPaint();
    public int getObjectY();
    public int getObjectX();
    public void update();
    public int getRadius();
    public int getColor();
}
