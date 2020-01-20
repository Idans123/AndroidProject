package com.example.balloonsworld;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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

    public GameView(Context context) {
        super(context);
        ballon = BitmapFactory.decodeResource(getResources(),R.drawable.balloon);
        balloonX=canvasWidth/2 - ballon.getWidth()/2;

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
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if(event.getAction() == MotionEvent.ACTION_POINTER_DOWN)
//        {
//           balloonX=(int)event.getX();
//        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                balloonX=(int)event.getX();
        }
        return true;
    }

}
