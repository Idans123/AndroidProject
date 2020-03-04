package com.example.balloonsworld;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class HighScoresActivity extends AppCompatActivity {
    private List<HashMap<String, Object>> stomList;
    private SharedPreferences sharedPreferences;
    private KonfettiView konfettiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_scores_layout);
        getSupportActionBar().hide();
        Button backBtn = findViewById(R.id.backfromhighscore);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sharedPreferences = getSharedPreferences("storage",MODE_PRIVATE);
        ListView listView = findViewById(R.id.high_scores_list);
        stomList = new ArrayList<HashMap<String, Object>>();
//        setSPMockData();
        getUsersFromSP();
        String [] from = {"index","name","points"};
        int [] ids = {R.id.index_high_score_tv, R.id.name_high_score_tv, R.id.points_high_score_tv};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,stomList,R.layout.high_score_cell,from,ids);
        listView.setAdapter(simpleAdapter);
        startKonfetti();
    }
    private void setSPMockData(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("player_name1","itzik");
        editor.putString("player_score1","300");
        editor.putString("player_name2","moshe");
        editor.putString("player_score2","200");
        editor.putString("player_name3","david");
        editor.putString("player_score3","100");
        editor.putString("player_name4","avner");
        editor.putString("player_score4","50");
        editor.putString("player_name5","yosi");
        editor.putString("player_score5","10");
        editor.commit();
    }
    private void getUsersFromSP(){
        HashMap<String,Object> stringToObjectMap = new HashMap<String, Object>();
        for(int i=1;i<=10;i++){
            if(sharedPreferences.contains("player_name"+i)&&sharedPreferences.contains("player_score"+i)){
                stringToObjectMap.put("index",i);
                stringToObjectMap.put("name",sharedPreferences.getString("player_name"+i,""));
                stringToObjectMap.put("points",sharedPreferences.getString("player_score"+i,""));
                stomList.add(stringToObjectMap);
            }
            stringToObjectMap = new HashMap<String, Object>();;
        }
    }
    private void startKonfetti(){
        konfettiView =  findViewById(R.id.konfettiView);
        konfettiView.build()
                .addColors(Color.YELLOW,Color.GREEN,Color.MAGENTA, Color.RED, Color.BLUE)
                .setDirection(0.0,359.0)
                .setSpeed(3f,5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT,Shape.CIRCLE)
                .addSizes(new Size(12,5))
                .setPosition(-50,konfettiView.getWidth()+50f,-50f,-50f)
                .streamFor(300,5000L);
    }
}
