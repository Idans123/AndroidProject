package com.example.balloonsworld.gameobjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.balloonsworld.R;

public class TutorialManager implements IGameObjects {
    Context context;
    private int tutorialY;
    private Paint paint;
    int tutorialStage=0;

    public  TutorialManager(Context context){
        this.context=context;
        this.tutorialY = 0;
        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(20*context.getResources().getDisplayMetrics().scaledDensity);
        paint.setTypeface(Typeface.DEFAULT_BOLD);

    }
    @Override
    public int getObjectY() {
        return this.tutorialY;
    }

    @Override
    public int getObjectX() {
        return 0;
    }

    @Override
    public void update() {
        tutorialY+=10;
    }

    @Override
    public void drawNow(Canvas canvas) {
        if(this.getObjectY()>canvas.getHeight()+300){
            this.tutorialY = 0;
        }

        if(tutorialStage==0){
            canvas.drawText(context.getResources().getString(R.string.welcome),canvas.getWidth()/8,this.tutorialY,this.paint);
        }
        else if(tutorialStage==1){
            canvas.drawText(context.getResources().getString(R.string.uses_accelerometer),canvas.getWidth()/10,this.tutorialY,this.paint);
            canvas.drawText(context.getResources().getString(R.string.to_control),canvas.getWidth()/10,this.tutorialY+100,this.paint);
        }
        else if(tutorialStage==2){
            canvas.drawText(context.getResources().getString(R.string.follow_the_next),canvas.getWidth()/10,this.tutorialY,this.paint);
            canvas.drawText(context.getResources().getString(R.string.to_finish_tutorial),canvas.getWidth()/10,this.tutorialY+100,this.paint);
        }
        else if(tutorialStage==3){
            canvas.drawText(context.getResources().getString(R.string.tilt_your_phone),canvas.getWidth()/10,this.tutorialY,this.paint);
            canvas.drawText(context.getResources().getString(R.string.most_right),canvas.getWidth()/10,this.tutorialY+100,this.paint);
        }
        else if(tutorialStage==4){
            canvas.drawText(context.getResources().getString(R.string.good_job),canvas.getWidth()/10,this.tutorialY,this.paint);
            canvas.drawText(context.getResources().getString(R.string.now_to_the_left),canvas.getWidth()/10,this.tutorialY+100,this.paint);
        }
        else if(tutorialStage==5){
            canvas.drawText(context.getResources().getString(R.string.your_doing_great),canvas.getWidth()/10,this.tutorialY,this.paint);
            canvas.drawText(context.getResources().getString(R.string.now_catch),canvas.getWidth()/10,this.tutorialY+100,this.paint);
        }
        else if(tutorialStage==6){
            canvas.drawText(context.getResources().getString(R.string.excellent),canvas.getWidth()/10,this.tutorialY,this.paint);
            canvas.drawText(context.getResources().getString(R.string.final_drill),canvas.getWidth()/10,this.tutorialY+100,this.paint);
            canvas.drawText(context.getResources().getString(R.string.avoid),canvas.getWidth()/10,this.tutorialY+200,this.paint);
        }
    }

    @Override
    public boolean hitCheker(int canvasHeight, Bitmap ballon, int balloonX) {
        return false;
    }

    public int nextTutorial(){
        tutorialStage++;
        tutorialY=0;
        return  tutorialStage;
    }
}
