package com.example.balloonsworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText playerNameET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("storage",MODE_PRIVATE);
        playerNameET = findViewById(R.id.playerNameET);
        playerNameET.setText(sharedPreferences.getString("player_name",""));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("player_name",playerNameET.getText().toString());
        editor.commit();
    }
}
