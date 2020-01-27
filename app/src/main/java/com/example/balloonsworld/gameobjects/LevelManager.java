package com.example.balloonsworld.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.HashMap;

public class LevelManager implements IGameObjects {

    private int levelY;
    private Paint paint;
    private int level;
    private HashMap<Integer,Integer> levelToScoreDict= new HashMap<Integer, Integer>();

    public LevelManager(int level) {
        initDict();
        this.level=level;
        this.levelY = 0;
        paint=new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(200);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    public int getObjectY() {
        return levelY;
    }

    @Override
    public int getObjectX() {
        return 0;
    }

    @Override
    public void update() {
        this.levelY+=10;
    }

    @Override
    public void drawNow(Canvas canvas) {
        canvas.drawText(this.level+"",canvas.getWidth()/2,this.levelY,this.paint);
    }

    @Override
    public boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX) {
        return false;
    }

    public int getLevel() {
        return level;
    }

    public boolean progressManager(int score){
        if(levelToScoreDict.get(level)<=score){
            this.levelY=0;
            level++;
            return true;
        }
        else{
            return false;
        }
    }
    private void initDict()
    {
        levelToScoreDict.put(1,50);
        levelToScoreDict.put(2,100);
        levelToScoreDict.put(3,150);
        levelToScoreDict.put(4,400);
        levelToScoreDict.put(5,500);
        levelToScoreDict.put(6,600);
        levelToScoreDict.put(7,700);
    }
}
