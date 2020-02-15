package com.example.balloonsworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LevelsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    int levelsToDisable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_layout);
        getSupportActionBar().hide();
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
            startActivityForResult(intent,1);
            }
        });
        clearAndCreateAllLevelsBtns(levelListener);
    }

    private void clearAndCreateAllLevelsBtns(LevelListener levelListener) {
        sharedPreferences = getSharedPreferences("storage",MODE_PRIVATE);
        levelsToDisable = sharedPreferences.getInt("level",0);
        LinearLayout btnsLayout1 = findViewById(R.id.levelsLL1);
        btnsLayout1.removeAllViews();
        for(int i=1;i<=5;i++){
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParms=new LinearLayout.LayoutParams((int) (50*getResources().getDisplayMetrics().density), (int) (50*getResources().getDisplayMetrics().density));
            layoutParms.setMarginEnd((int) (50 / Resources.getSystem().getDisplayMetrics().density));
            button.setLayoutParams(layoutParms);
            button.setText(i+"");
            button.setOnClickListener(levelListener);
            button.setBackground(getResources().getDrawable(R.drawable.green_btn));
            if(i>levelsToDisable) {
                button.setEnabled(false);
                button.setBackground(getResources().getDrawable(R.drawable.red_btn));
            }
            btnsLayout1.addView(button);
        }

        LinearLayout btnsLayout2 = findViewById(R.id.levelsLL2);
        btnsLayout2.removeAllViews();
        for(int i=6;i<=10;i++){
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParms=new LinearLayout.LayoutParams((int) (50*getResources().getDisplayMetrics().density), (int) (50*getResources().getDisplayMetrics().density));
            layoutParms.setMarginEnd((int) (50 / Resources.getSystem().getDisplayMetrics().density));
            button.setLayoutParams(layoutParms);
            button.setText(i+"");
            button.setOnClickListener(levelListener);
            button.setBackground(getResources().getDrawable(R.drawable.green_btn));
            if(i>levelsToDisable) {
                button.setEnabled(false);
                button.setBackground(getResources().getDrawable(R.drawable.red_btn));
            }
            btnsLayout2.addView(button);
        }

        LinearLayout btnsLayout3 = findViewById(R.id.levelsLL3);
        btnsLayout3.removeAllViews();
        for(int i=11;i<=15;i++){
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParms=new LinearLayout.LayoutParams((int) (50*getResources().getDisplayMetrics().density), (int) (50*getResources().getDisplayMetrics().density));
            layoutParms.setMarginEnd((int) (50 / Resources.getSystem().getDisplayMetrics().density));
            button.setLayoutParams(layoutParms);
            button.setText(i+"");
            button.setOnClickListener(levelListener);
            button.setBackground(getResources().getDrawable(R.drawable.green_btn));
            if(i>levelsToDisable) {
                button.setEnabled(false);
                button.setBackground(getResources().getDrawable(R.drawable.red_btn));
            }
            btnsLayout3.addView(button);
        }

        LinearLayout btnsLayout4 = findViewById(R.id.levelsLL4);
        btnsLayout4.removeAllViews();
        for(int i=16;i<=20;i++){
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParms=new LinearLayout.LayoutParams((int) (50*getResources().getDisplayMetrics().density), (int) (50*getResources().getDisplayMetrics().density));
            layoutParms.setMarginEnd((int) (50 / Resources.getSystem().getDisplayMetrics().density));
            button.setLayoutParams(layoutParms);
            button.setText(i+"");
            button.setOnClickListener(levelListener);
            button.setBackground(getResources().getDrawable(R.drawable.green_btn));
            if(i>levelsToDisable) {
                button.setEnabled(false);
                button.setBackground(getResources().getDrawable(R.drawable.red_btn));
            }
            btnsLayout4.addView(button);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                LevelListener levelListener = new LevelListener();
                clearAndCreateAllLevelsBtns(levelListener);
            }
        }
    }

    private class LevelListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
            intent.putExtra("player_name",getIntent().getStringExtra("player_name"));
            intent.putExtra("level",Integer.parseInt(((Button)v).getText().toString()));
            startActivityForResult(intent,1);
        }
    }

}
