package com.example.baky.wakeup.View.Calendar;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.baky.wakeup.R;
import com.example.baky.wakeup.Util.ApplicationController;

import java.sql.Time;
import java.util.Date;

import static java.security.AccessController.getContext;

public class CalendarAlarm extends Activity {

    TimePicker timePicker;
    Button button;
    ImageButton destSearchBtn;
    TextView textview6;
    EditText editText1;

    int day, month, year;
    int hour, min;
    int position;

    public ApplicationController applicationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_alarm_setting);

        destSearchBtn = (ImageButton) findViewById(R.id.imageButton4);
        button = (Button) findViewById(R.id.button);
        textview6 = (TextView) findViewById(R.id.textView6);
        editText1 = (EditText)findViewById(R.id.editText2);
        timePicker = (TimePicker) findViewById(R.id.timePicker2);

        day = getIntent().getIntExtra("day",-1);
        month = getIntent().getIntExtra("month",-1);
        year = getIntent().getIntExtra("year",-1);
        position = getIntent().getIntExtra("position", -1);
//        Toast.makeText(this,"day : " + day+"month :" +month,Toast.LENGTH_SHORT).show();
        textview6.setText(""+year+". "+month+". "+day);

        destSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MapActivity.class);
                Log.d("search",editText1.getText().toString());
                intent.putExtra("search",editText1.getText().toString());
                startActivityForResult(intent,10);
            }
        });


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

//        GridView monthView;
//        MonthAdapter monthViewAdapter;
//
//        monthView = (GridView) findViewById(R.id.monthView);
//        monthViewAdapter = new MonthAdapter(getApplicationContext());
//        monthView.setAdapter(monthViewAdapter);

        finish();
        //Toast.makeText(this,"hour : " + hour + "min :" + min, Toast.LENGTH_SHORT).show();
    }

}