package com.example.balloonsworld.gameobjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.balloonsworld.R;

import java.util.HashMap;

public class LevelManager implements IGameObjects {

    private int levelY;
    private Paint paint;
    private int level;
    private HashMap<Integer,Integer> levelToScoreDict= new HashMap<Integer, Integer>();
    private Context context;
    private String [] scoresFromRes;

    public LevelManager(int level, Context context) {
        this.level=level;
        this.levelY = 0;
        paint=new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(200);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        this.context = context;
        initDict();
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
        this.levelY+=this.level+5;
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
        this.scoresFromRes = this.context.getResources().getStringArray(R.array.levelsToScore);
        for(int i=0; i<scoresFromRes.length;i++)
            levelToScoreDict.put(i+1, Integer.valueOf(scoresFromRes[i]));
    }
}
