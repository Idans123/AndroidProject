package com.example.balloonsworld;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HighScoresActivity extends AppCompatActivity {
    private List<HashMap<String, Object>> stomList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_scores_layout);
        sharedPreferences = getSharedPreferences("storage",MODE_PRIVATE);
        ListView listView = findViewById(R.id.high_scores_list);
        stomList = new ArrayList<HashMap<String, Object>>();
        getUsersFromSP();
        String [] from = {"index","name","points"};
        int [] ids = {R.id.index_high_score_tv, R.id.name_high_score_tv, R.id.points_high_score_tv};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,stomList,R.layout.high_score_cell,from,ids);
        listView.setAdapter(simpleAdapter);
    }
    private void getUsersFromSP(){
        HashMap<String,Object> stringToObjectMap = new HashMap<String, Object>();
        for(int i=1;i<=10;i++){
            if(sharedPreferences.contains("player_name"+i)&&sharedPreferences.contains("player_score"+i)){
                stringToObjectMap.put("index",i+".");
                stringToObjectMap.put("name",sharedPreferences.getString("player_name"+i,""));
                stringToObjectMap.put("points",sharedPreferences.getString("player_score"+i,""));
                stomList.add(stringToObjectMap);
            }
            stringToObjectMap = new HashMap<String, Object>();;
        }
    }
}
