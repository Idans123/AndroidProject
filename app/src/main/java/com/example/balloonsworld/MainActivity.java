package com.example.balloonsworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText playerNameET;
    public static Activity fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fa=this;
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("storage",MODE_PRIVATE);
        playerNameET = findViewById(R.id.playerNameET);
        playerNameET.setText(sharedPreferences.getString("player_name_last_user",""));
        Button acceptBtn = findViewById(R.id.acceptBtn);
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playerNameET.getText().toString().length()<1){
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    intent.putExtra("player_name",playerNameET.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("player_name_last_user",playerNameET.getText().toString());
        editor.commit();
    }
    
}
