package com.example.balloonsworld;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class GameView extends View {
    private Bitmap ballon;
    private int canvasHeight;
    private int canvasWidth;
    private int balloonX;
    private int coinX, coinY=0, coinSpeed=16;
    private Paint coinPaint = new Paint();
    private int score=0;
    private boolean touch = false;


    public GameView(Context context) {
        super(context);
        ballon = BitmapFactory.decodeResource(getResources(),R.drawable.balloon);
        balloonX=canvasWidth/2 - ballon.getWidth()/2;

        coinPaint.setColor(Color.YELLOW);
        coinPaint.setAntiAlias(false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
//        canvas.drawBitmap(ballon,canvasWidth/2 - ballon.getWidth()/2,canvasHeight - ballon.getHeight()*2,null);

        int minBallonX=ballon.getWidth();
        int maxBallonX = canvasWidth - ballon.getWidth();

        if(balloonX<minBallonX){
            balloonX=minBallonX;
        }
        if(balloonX >maxBallonX){
            balloonX=maxBallonX;
        }
        canvas.drawBitmap(ballon,balloonX,canvasHeight - ballon.getHeight()*2,null);

        coinY+=coinSpeed;
        if(hitCheker(coinX,coinY))
        {
            score+=10;
            coinY+=1000;
            System.out.println("Score: "+score);
        }
        if(coinY>canvasHeight){
            coinY=0;
            coinX=(int)Math.floor(Math.random()*(maxBallonX-minBallonX))+minBallonX;
        }
        canvas.drawCircle(coinX,coinY,25,coinPaint);
        hitCheker(coinX,coinY);
    }
    public boolean hitCheker(int x, int y){
        return(canvasHeight - ballon.getHeight()*2<y &&
                (canvasHeight - ballon.getHeight()*2+ballon.getHeight())>y &&
                x>ballon.getWidth() && x<ballon.getWidth()+balloonX);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                balloonX=(int)event.getX();
//                System.out.println(event.getX()+","+event.getY());
        }
        return true;
    }

}
