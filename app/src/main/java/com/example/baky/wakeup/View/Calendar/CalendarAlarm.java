package com.example.baky.wakeup.View.Calendar;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.baky.wakeup.R;
import com.example.baky.wakeup.Util.ApplicationController;

import java.sql.Time;
import java.util.Date;

public class CalendarAlarm extends Activity {

    TimePicker timePicker;
    Button button;
    TextView textview6;

    int day, month, year;
    int hour, min;

    public ApplicationController applicationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_alarm_setting);

        button = (Button) findViewById(R.id.button);
        textview6 = (TextView) findViewById(R.id.textView6);
        timePicker = (TimePicker) findViewById(R.id.timePicker2);

        day = getIntent().getIntExtra("day",-1);
        month = getIntent().getIntExtra("month",-1);
        year = getIntent().getIntExtra("year",-1);
//        Toast.makeText(this,"day : " + day+"month :" +month,Toast.LENGTH_SHORT).show();
        textview6.setText(""+year+". "+month+". "+day);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onButtonClicked(View v)
    {
        hour = timePicker.getHour();
        min = timePicker.getMinute();

        Date date = new Date();
        date.setYear(year);
        date.setMonth(month);
        date.setDate(day);
        date.setHours(hour);
        date.setMinutes(min);
        CalendarData CData = new CalendarData(37.570841, 126.985302, date);
        applicationController = (ApplicationController) getApplicationContext();
        applicationController.setJsonString(CData);
        //Toast.makeText(this,"hour : " + hour + "min :" + min, Toast.LENGTH_SHORT).show();
    }
}