package com.example.hairdesigner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner hairCutSpinner;
    private ImageView customerSelfie;
    final int CAMERA_REQUEST = 1;
    final int GALLERY_REQUEST = 2;
    private boolean isCustomerSelfie;
    EditText customerNameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customerNameET = findViewById(R.id.nameET);

        spinnerResortsInitialize();

        customerSelfie = findViewById(R.id.customerSelfie);
        ImageButton openCameraBtn = findViewById(R.id.opencamera_btn);
        openCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST);
            }
        });
        ImageButton openStorageBtn = findViewById(R.id.opengallery_btn);
        openStorageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, GALLERY_REQUEST);
                }
            }
        });

        Button nextBtn = findViewById(R.id.next_btn_to_second_page);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((customerNameET.getText().toString().length()>1)&&isCustomerSelfie){
                    Intent intent = new Intent(MainActivity.this,DateAndTimeSecondActivity.class);
                    intent.putExtra("customerName",customerNameET.getText().toString());
                    intent.putExtra("hairCutType", hairCutSpinner.getSelectedItem().toString());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, getString(R.string.finish_fill_all_details), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            customerSelfie.setImageBitmap(bitmap);
            isCustomerSelfie=true;
        }
        else if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            Bitmap thumbnail = data.getParcelableExtra("data");
            Uri uri = data.getData();
            customerSelfie.setImageURI(uri);
            isCustomerSelfie=true;
        }
    }

    private void spinnerResortsInitialize(){
        hairCutSpinner = (Spinner) findViewById(R.id.typeOfHaircutSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.haircuts_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairCutSpinner.setAdapter(adapter);
    }
}
