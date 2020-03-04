package com.example.hairdesigner;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.actions.NoteIntents;


public class ContactUsThirdActivity extends AppCompatActivity {
    private final String OUR_PHONE_NUMBER = "03-1234567";
    private final int CALL_PERMISSION_REQUEST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us_third_activity);
        TextView summaryTV = findViewById(R.id.summary_tv);
        summaryTV.setText(getString(R.string.we_will_see_you_on)+" "+getIntent().getStringExtra("date")+" "+getString(R.string.at)+" "+getIntent().getStringExtra("time"));

        ImageButton callUsBtn = findViewById(R.id.call_us_btn);
        ImageButton addToContactBtn = findViewById(R.id.add_contact_btn);
        ImageButton navigateBtn = findViewById(R.id.navigate_us_btn);
        ImageButton websearchBtn = findViewById(R.id.websearch_btn);

        callUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=23){
                    int hasCallPermission = checkSelfPermission(Manifest.permission.CALL_PHONE);
                    if(hasCallPermission == PackageManager.PERMISSION_GRANTED){
                        callUs();
                    }
                    else {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},CALL_PERMISSION_REQUEST);
                    }
                }
                else{ callUs();}
            }
        });

        addToContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertContact();
            }
        });

        navigateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToUs();
            }
        });

        websearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchWeb(getString(R.string.search_query));
            }
        });
    }
    private void callUs(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+OUR_PHONE_NUMBER));
        startActivity(intent);
    }
    private void searchWeb(String query) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private void createNote(String text) {
        Intent intent = new Intent(NoteIntents.ACTION_CREATE_NOTE)
                .putExtra(NoteIntents.EXTRA_NAME, "Note to itzik")
                .putExtra(NoteIntents.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private void insertContact() {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, "Itzik's Saloon");
        intent.putExtra(ContactsContract.Intents.Insert.PHONE,OUR_PHONE_NUMBER);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private void navigateToUs(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:32.084010,34.829820"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CALL_PERMISSION_REQUEST){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                callUs();
            }
            else{
                Toast.makeText(this, getString(R.string.accept_permission), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
