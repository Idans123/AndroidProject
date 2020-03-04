package com.example.hairdesigner;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateAndTimeSecondActivity extends AppCompatActivity {
    private Button dateBtn;
    private Button timeBtn;
    private ImageButton addToCalendarBtn, addToAlarmBtn;
    private GregorianCalendar gregorianCalendar;
    private boolean isTimeSet, isDateSet;
    private int minToAlarm, hoursToAlarm;
    private final int ALARM_PERMISSION_REQUEST=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_and_time_second_activity);
        gregorianCalendar = new GregorianCalendar();
        TextView helloTV = findViewById(R.id.hello_tv);
        TextView priceTV = findViewById(R.id.price_tv);
        priceTV.setText(
                priceTV.getText().toString()
                +" "
                +getPriceByHairCutType(getIntent().getStringExtra("hairCutType"))
                +" "
                +getString(R.string.coin_type)
        );
        helloTV.setText(
                helloTV.getText().toString()
                +" "
                +getIntent().getStringExtra("customerName")
                +", "+getString(R.string.thank_you_choose) +"!"
        );
        dateBtn = findViewById(R.id.pickDateBtn);
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialogStart datePicker=new DatePickerDialogStart();
                datePicker.showDatePickerDialog();
            }
        });
        timeBtn = findViewById(R.id.pickTimeBtn);
        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialogStart timePickerDialogStart = new TimePickerDialogStart();
                timePickerDialogStart.showTimePickerDialog();
            }
        });
        addToCalendarBtn = findViewById(R.id.addToCalendarBtn);
        addToCalendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long start=gregorianCalendar.getTimeInMillis();
                long end = gregorianCalendar.getTimeInMillis()+3600000;
                if(isDateSet&&isTimeSet)
                    addEvent(getHairCutEventTitle(getIntent().getStringExtra("hairCutType")),start,end);
                else{
                    Toast.makeText(DateAndTimeSecondActivity.this, getString(R.string.finish_fill_all_details), Toast.LENGTH_SHORT).show();
                }
            }
        });
//        addToAlarmBtn = findViewById(R.id.addToAlarmBtn);
//        addToAlarmBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isTimeSet) {
//                    if(Build.VERSION.SDK_INT>=23){
//                        int isAlarmPermission=checkSelfPermission(Manifest.permission.SET_ALARM);
//                        if(isAlarmPermission == PackageManager.PERMISSION_GRANTED){
//                            createAlarm(getString(R.string.alarm_message), hoursToAlarm, minToAlarm);
//                        }
//                        else {
//                            requestPermissions(new String[]{Manifest.permission.SET_ALARM},ALARM_PERMISSION_REQUEST);
//                        }
//                    }
//                    createAlarm(getString(R.string.alarm_message), hoursToAlarm, minToAlarm);
//                }
//                else
//                    Toast.makeText(DateAndTimeSecondActivity.this, getString(R.string.finish_fill_all_details), Toast.LENGTH_SHORT).show();
//            }
//        });
        Button nextBtn = findViewById(R.id.next_btn_to_third_page);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDateSet&&isTimeSet) {
                    Intent intent = new Intent(DateAndTimeSecondActivity.this, ContactUsThirdActivity.class);
                    intent.putExtra("date", dateBtn.getText().toString());
                    intent.putExtra("time", timeBtn.getText().toString());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(DateAndTimeSecondActivity.this, getString(R.string.finish_fill_all_details), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void addEvent(String title, long begin, long end) {
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                    .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                    .putExtra(AlarmClock.EXTRA_HOUR, hour)
                    .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private int getPriceByHairCutType(String type){
        if(type.equals(getString(R.string.haircut_man_spinner))){
            return 50;
        }
        else if (type.equals(getString(R.string.haircut_japanese_blowout))){
            return 1000;
        }
        else if (type.equals(getString(R.string.haircut_hair_coloring))){
            return 150;
        }
        else{return 100;}
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == ALARM_PERMISSION_REQUEST){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                createAlarm(getString(R.string.alarm_message),hoursToAlarm,minToAlarm);
            }
            else{
                Toast.makeText(this, getString(R.string.accept_permission), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getHairCutEventTitle(String event){
        if(event.equals(getString(R.string.haircut_man_spinner))){
            return getString(R.string.appointment_at_saloon)+ "- "+getString(R.string.man_haircut_event);
        }
        else if (event.equals(getString(R.string.haircut_japanese_blowout))){
            return getString(R.string.appointment_at_saloon)+ "- "+getString(R.string.japanese_blowout_event);
        }
        else if (event.equals(getString(R.string.haircut_hair_coloring))){
            return getString(R.string.appointment_at_saloon)+ "- "+getString(R.string.hair_coloring_event);
        }
        else{
            return getString(R.string.appointment_at_saloon)+ "- "+getString(R.string.woman_haircut_event);
        }
    }
    private class DatePickerDialogStart implements DatePickerDialog.OnDateSetListener{
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateBtn.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            gregorianCalendar.set(GregorianCalendar.YEAR, year);
            gregorianCalendar.set(GregorianCalendar.MONTH, month);
            gregorianCalendar.set(GregorianCalendar.DATE, dayOfMonth);
            isDateSet=true;
        }
        public void showDatePickerDialog(){
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    DateAndTimeSecondActivity.this,this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        }
    }
    private class TimePickerDialogStart implements TimePickerDialog.OnTimeSetListener{
        int hour;
        int minuteOfHour;
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour=hourOfDay;
            minuteOfHour=minute;
            String addZeroToString="";
            if(minute<10){
                addZeroToString="0";
            }
            timeBtn.setText(hourOfDay+":"+addZeroToString+minute);
            calculateTimeToAlarm(hourOfDay,minute);
            gregorianCalendar.set(GregorianCalendar.HOUR_OF_DAY,hourOfDay);
            gregorianCalendar.set(GregorianCalendar.MINUTE,minute);
            isTimeSet=true;
        }
        public void showTimePickerDialog(){
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    DateAndTimeSecondActivity.this,this,
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE),
                    true
            );
            timePickerDialog.show();
        }
    }
    public void calculateTimeToAlarm(int hour, int minutes){
        minToAlarm = minutes-30;
        hoursToAlarm = hour;
        if(minToAlarm<0){
            minToAlarm+=60;
            hoursToAlarm--;
        }
    }

}
