package com.example.balloonsworld.gameobjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class TutorialManager implements IGameObjects {
    Context context;
    private int tutorialY;
    private Paint paint;
    int tutorialStage=0;

    public  TutorialManager(Context context){
        this.context=context;
        this.tutorialY = 0;
        paint=new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(100);
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
            canvas.drawText("Welcome to ballons world",canvas.getWidth()/8,this.tutorialY,this.paint);
        }
        else if(tutorialStage==1){
            canvas.drawText("The game uses accelerometer",canvas.getWidth()/10,this.tutorialY,this.paint);
            canvas.drawText("to control the ballon",canvas.getWidth()/10,this.tutorialY+100,this.paint);
        }
        else if(tutorialStage==2){
            canvas.drawText("Follow the next instructions",canvas.getWidth()/10,this.tutorialY,this.paint);
            canvas.drawText("to finish the tutorial",canvas.getWidth()/10,this.tutorialY+100,this.paint);
        }
        else if(tutorialStage==3){
            canvas.drawText("Tilt your phone to the",canvas.getWidth()/10,this.tutorialY,this.paint);
            canvas.drawText("right and touch the most right",canvas.getWidth()/10,this.tutorialY+100,this.paint);
        }
        else if(tutorialStage==4){
            canvas.drawText("Good job!!!",canvas.getWidth()/10,this.tutorialY,this.paint);
            canvas.drawText("Now to the left",canvas.getWidth()/10,this.tutorialY+100,this.paint);
        }
        else if(tutorialStage==5){
            canvas.drawText("Your doing great!!!",canvas.getWidth()/10,this.tutorialY,this.paint);
            canvas.drawText("Now catch the coin",canvas.getWidth()/10,this.tutorialY+100,this.paint);
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
