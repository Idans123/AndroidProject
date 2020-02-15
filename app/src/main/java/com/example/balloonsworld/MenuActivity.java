package com.example.balloonsworld;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.fa.finish();
        setContentView(R.layout.menu_activity);
        getSupportActionBar().hide();
        balloonAnimation();
        TextView helloUserTV = findViewById(R.id.helloUserTV);
        helloUserTV.setText(helloUserTV.getText().toString() +" "+getIntent().getStringExtra("player_name") );

        YoYo.with(Techniques.Shake).duration(5000).repeat(YoYo.INFINITE).repeatMode(ObjectAnimator.REVERSE).playOn(findViewById(R.id.helloUserTV));

        Button exitBtn = findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitDialog();
            }
        });
        final Button startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.RotateIn).duration(2000).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent intent = new Intent(MenuActivity.this, LevelsActivity.class);
                        intent.putExtra("player_name",getIntent().getStringExtra("player_name"));
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).playOn(startBtn);
            }
        });
        final Button highScoresBtn = findViewById(R.id.highScoresBtn);
        highScoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.RotateIn).duration(2000).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent intent = new Intent(MenuActivity.this, HighScoresActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).playOn(highScoresBtn);

            }
        });
    }
    private void showExitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
        builder.setTitle(getString(R.string.exit))
                .setMessage(getString(R.string.exit_message))
                .setPositiveButton(getString(R.string.yes), new AlertDialogListener())
                .setNegativeButton(getString(R.string.no), new AlertDialogListener())
                .show();
    }
    @Override
    public void onBackPressed() {
        showExitDialog();
    }
    private void balloonAnimation(){
        ImageView balloonIV = findViewById(R.id.balloonIV);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(balloonIV,"translationY",200).setDuration(1500);
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }
    private class AlertDialogListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which == DialogInterface.BUTTON_POSITIVE){
                System.exit(0);
            }
        }
    }
}
