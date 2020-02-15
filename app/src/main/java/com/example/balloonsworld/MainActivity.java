package com.example.balloonsworld;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText playerNameET;
    TextView welcomeTV;
    public static Activity fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fa=this;
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        sharedPreferences = getSharedPreferences("storage",MODE_PRIVATE);
        welcomeTV = findViewById(R.id.welcomeTV);
        YoYo.with(Techniques.FlipInY).duration(2000).playOn(welcomeTV);

        playerNameET = findViewById(R.id.playerNameET);
        playerNameET.setText(sharedPreferences.getString("player_name_last_user",""));
        final Button acceptBtn = findViewById(R.id.acceptBtn);
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playerNameET.getText().toString().length()<1){
                    YoYo.with(Techniques.Tada).duration(2000).delay(1000).playOn(acceptBtn);
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                }
                else{
                    YoYo.with(Techniques.ZoomOutDown).duration(2000).withListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                            intent.putExtra("player_name",playerNameET.getText().toString());
                            startActivity(intent);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    }).playOn(acceptBtn);
                }
            }
        });
        YoYo.with(Techniques.RollOut).duration(2000).delay(1000).repeat(YoYo.INFINITE).repeatMode(ObjectAnimator.REVERSE).playOn(findViewById(R.id.balloonsIV));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("player_name_last_user",playerNameET.getText().toString());
        editor.commit();
    }
    
}
