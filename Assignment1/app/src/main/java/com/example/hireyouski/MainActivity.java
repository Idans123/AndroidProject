package com.example.hireyouski;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity{
    private Button startDatebtn;
    private int numOfSkiingDays;
    private RadioGroup radioGroup;
    private boolean isStartDateSet;
    private EditText fullnameET;
    private EditText emailET;
    private EditText numberOfDaysET;
    private int userChoice;
    static final int SNOWBOARD_CHOICE=0;
    static final int SKI_CHOICE=1;
    private Spinner spinner;
    private boolean isEmail, isFullName, isGearSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerResortsInitialize();
        radioGroup= findViewById(R.id.gearRG);
        startDatebtn=findViewById(R.id.startDateBtn);
        numberOfDaysET=findViewById(R.id.numberOfDaysET);
        fullnameET=findViewById(R.id.fullNameET);
        fullnameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()<4){
                    fullnameET.getBackground().setColorFilter(getResources().getColor(R.color.red),
                            PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.least_char_toast), Toast.LENGTH_SHORT).show();
                    isFullName=false;
                }
                else{
                    fullnameET.getBackground().setColorFilter(getResources().getColor(R.color.green),
                            PorterDuff.Mode.SRC_ATOP);
                    isFullName=true;
                }
            }
        });
        emailET=findViewById(R.id.email_ET);
        emailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()<4){
                    emailET.getBackground().setColorFilter(getResources().getColor(R.color.red),
                            PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.least_char_toast), Toast.LENGTH_SHORT).show();
                    isEmail=false;
                }
                else{
                    emailET.getBackground().setColorFilter(getResources().getColor(R.color.green),
                            PorterDuff.Mode.SRC_ATOP);
                    isEmail=true;
                }
            }
        });
        numberOfDaysET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().matches("([1-9]|1[0-9]|20)")){
                    numberOfDaysET.getBackground().setColorFilter(getResources().getColor(R.color.green),
                            PorterDuff.Mode.SRC_ATOP);
                    numOfSkiingDays=Integer.parseInt(s.toString());
                }
                else{
                    numberOfDaysET.getBackground().setColorFilter(getResources().getColor(R.color.red),
                            PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(getApplicationContext(),getString(R.string.error_number_of_days), Toast.LENGTH_SHORT).show();

                }
            }
        });
        startDatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialogStart datePicker=new DatePickerDialogStart();
                datePicker.showDatePickerDialog();
                    isStartDateSet=true;
            }
        });
        Button goToPaymentBtn = findViewById(R.id.goToPaymentBtn);
        goToPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmail&&isGearSelected&&isStartDateSet&&isFullName&&numOfSkiingDays>=1){
//                    Toast.makeText(getApplicationContext(),"OK!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,PriceAndRatingActivity.class);
                    intent.putExtra("numberOfDays",numOfSkiingDays);
                    intent.putExtra("userChoice",userChoice);
                    intent.putExtra("resort",spinner.getSelectedItem().toString());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),getString(R.string.fill_all_details), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void spinnerResortsInitialize(){
        spinner = (Spinner) findViewById(R.id.resortsSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.resorts_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public void onCheckRadioButton(View v){
        int rbid=radioGroup.getCheckedRadioButtonId();
        ImageButton skiOrSnowboard = findViewById(R.id.skiOrSnowboardImg);
        RadioButton radioButton = findViewById(rbid);
        if(radioButton.getText().equals(getResources().getString(R.string.ski_rb))) {
            skiOrSnowboard.setImageDrawable(getResources().getDrawable(R.drawable.ski_icon));
            Toast.makeText(getApplicationContext(), getString(R.string.price_for_ski), Toast.LENGTH_LONG).show();
            isGearSelected=true;
            userChoice=SKI_CHOICE;
        }
        else {
            skiOrSnowboard.setImageDrawable(getResources().getDrawable(R.drawable.snowboarding_icon_15));
            Toast.makeText(getApplicationContext(), getString(R.string.price_for_snowboard), Toast.LENGTH_LONG).show();
            isGearSelected=true;
            userChoice=SNOWBOARD_CHOICE;
        }
    }
    public void onCheckBoxButton(View v){
        boolean checked = ((CheckBox)v).isChecked();
        ImageView helmet = findViewById(R.id.helmetImg);
        if(v.getId()==R.id.helmentCheckbox){
            if(checked){
                helmet.setBackground(getResources().getDrawable(R.drawable.helmet_selector_green));
            }
            else{
                helmet.setBackground(getResources().getDrawable(R.drawable.helmet_selector_red));
            }
        }
    }
    private class DatePickerDialogStart implements DatePickerDialog.OnDateSetListener{
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            startDatebtn.setText(dayOfMonth+"/"+(month+1)+"/"+year);
        }
        public void showDatePickerDialog(){
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this,this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

            );
            datePickerDialog.show();
        }
    }
}


