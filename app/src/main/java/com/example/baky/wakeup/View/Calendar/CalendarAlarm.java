package com.example.baky.wakeup.View.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baky.wakeup.R;

public class CalendarAlarm extends Activity {


    TextView textview6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_alarm_setting);


        textview6 = (TextView) findViewById(R.id.textView6);

        int day = getIntent().getIntExtra("day",-1);
        int month = getIntent().getIntExtra("month",-1);
        int year = getIntent().getIntExtra("year",-1);
//        Toast.makeText(this,"day : " + day+"month :" +month,Toast.LENGTH_SHORT).show();


        textview6.setText(""+year+"."+month+"."+day);
    }
}