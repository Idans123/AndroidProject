package com.example.balloonsworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LevelsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_layout);
        LevelListener levelListener = new LevelListener();
        Button backBtn = findViewById(R.id.backfromlevels);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button tutorialBtn = findViewById(R.id.tutorialBtn);
        tutorialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
            intent.putExtra("player_name",getIntent().getStringExtra("player_name"));
            intent.putExtra("level",0);
            startActivity(intent);
            }
        });
        LinearLayout btnsLayout1 = findViewById(R.id.levelsLL1);
        for(int i=1;i<=5;i++){
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(120,120);
            layoutParams.setMarginEnd(20);
            button.setLayoutParams(layoutParams);
            button.setText(i+"");
            button.setOnClickListener(levelListener);
            btnsLayout1.addView(button);
        }

        LinearLayout btnsLayout2 = findViewById(R.id.levelsLL2);
        for(int i=6;i<=10;i++){
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(120,120);
            layoutParams.setMarginEnd(20);
            button.setLayoutParams(layoutParams);
            button.setText(i+"");
            button.setOnClickListener(levelListener);
            btnsLayout2.addView(button);
        }

        LinearLayout btnsLayout3 = findViewById(R.id.levelsLL3);
        for(int i=11;i<=15;i++){
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(120,120);
            layoutParams.setMarginEnd(20);
            button.setLayoutParams(layoutParams);
            button.setText(i+"");
            button.setOnClickListener(levelListener);
            btnsLayout3.addView(button);
        }

        LinearLayout btnsLayout4 = findViewById(R.id.levelsLL4);
        for(int i=16;i<=20;i++){
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(120,120);
            layoutParams.setMarginEnd(20);
            button.setLayoutParams(layoutParams);
            button.setText(i+"");
            button.setOnClickListener(levelListener);
            btnsLayout4.addView(button);
        }
    }
    private class LevelListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
            intent.putExtra("player_name",getIntent().getStringExtra("player_name"));
            intent.putExtra("level",((Button)v).getText().toString());
            startActivity(intent);
        }
    }
}
