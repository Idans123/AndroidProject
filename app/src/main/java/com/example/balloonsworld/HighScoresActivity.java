package com.example.balloonsworld;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HighScoresActivity extends AppCompatActivity {
    private List<HashMap<String, Object>> stomList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_scores_layout);

        ListView listView = findViewById(R.id.high_scores_list);
        stomList = new ArrayList<HashMap<String, Object>>();
        setMockData();
        String [] from = {"index","name","points"};
        int [] ids = {R.id.index_high_score_tv, R.id.name_high_score_tv, R.id.points_high_score_tv};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,stomList,R.layout.high_score_cell,from,ids);
        listView.setAdapter(simpleAdapter);
    }
    private void setMockData(){
        HashMap<String,Object> stringToObjectMap = new HashMap<String, Object>();
        stringToObjectMap.put("index","1.");
        stringToObjectMap.put("name","David");
        stringToObjectMap.put("points","100");
        stomList.add(stringToObjectMap);

        stringToObjectMap = new HashMap<String, Object>();;
        stringToObjectMap.put("index","2.");
        stringToObjectMap.put("name","Yosi");
        stringToObjectMap.put("points","70");
        stomList.add(stringToObjectMap);

    }
}
