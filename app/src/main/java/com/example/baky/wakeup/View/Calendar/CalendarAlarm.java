package com.example.baky.wakeup.View.Calendar;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
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
import com.example.baky.wakeup.Util.StartReceiver;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import static java.security.AccessController.getContext;

public class CalendarAlarm extends Activity {

    TimePicker timePicker;
    Button button;
    ImageButton destSearchBtn;
    TextView textview6;
    EditText editText1;

    AlarmManager alarmManager;
    Calendar calendar;
    Intent mAlarmIntent;
    PendingIntent mPendingIntent;

    int day, month, year;
    int hour, min;
    int position;

    double latitude;
    double longitude;
    CalendarData CData;

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

        day = getIntent().getIntExtra("day",0);
        month = getIntent().getIntExtra("month",0);
        year = getIntent().getIntExtra("year",0);
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
        Log.d("dddddDate",date.toString());
        CData = new CalendarData(latitude,longitude,date);
        applicationController = (ApplicationController) getApplicationContext();
        applicationController.setJsonString(CData);

        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DATE,day);
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,min);
        calendar.set(Calendar.SECOND,0);


        mAlarmIntent = new Intent(getApplicationContext(), StartReceiver.class);
        mAlarmIntent.putExtra("FLAG",1);
        mAlarmIntent.putExtra("latitude",latitude);
        mAlarmIntent.putExtra("longitude",longitude);

        Log.d("dddLat",latitude+"");
        Log.d("dddLong",longitude+"");
        Log.d("ddddd","asdf");
        calendar.add(Calendar.MINUTE,-15);
        mPendingIntent = PendingIntent.getBroadcast(getApplicationContext(),1,mAlarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                mPendingIntent
        );

        finish();
        //Toast.makeText(this,"hour : " + hour + "min :" + min, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String name;
        if(resultCode == 1){
            name = data.getStringExtra("name");
            latitude = data.getDoubleExtra("latitude",0);
            longitude = data.getDoubleExtra("longitude",0);
            editText1.setText(name);
        }
    }
}