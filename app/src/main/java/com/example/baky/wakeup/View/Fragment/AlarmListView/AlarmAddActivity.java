package com.example.baky.wakeup.View.Fragment.AlarmListView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.baky.wakeup.R;
import com.example.baky.wakeup.Util.StartReceiver;
import com.example.baky.wakeup.View.Fragment.AlarmListTest;
import com.example.baky.wakeup.View.Fragment.AlarmTab;
import com.example.baky.wakeup.View.MainActivity;

import java.util.Calendar;

public class AlarmAddActivity extends Activity {

    boolean[] day= new boolean[7];
    CheckBox SUN,MON,TUE,WED,THU,FRI,SAT;

    AlarmManager alarmManager;
    Calendar calendar;
    Intent mAlarmIntent;
    PendingIntent mPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        Spinner spinner1 = (Spinner)findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //     tv.setText("position : " + position + parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //     tv.setText("position : " + position + parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });




       // CheckBox SUN,MON,TUE,WED,THU,FRI,SAT;
        SUN = (CheckBox)findViewById(R.id.checkBox8);
        MON = (CheckBox)findViewById(R.id.checkBox9);
        TUE = (CheckBox)findViewById(R.id.checkBox10);
        WED = (CheckBox)findViewById(R.id.checkBox11);
        THU = (CheckBox)findViewById(R.id.checkBox12);
        FRI = (CheckBox)findViewById(R.id.checkBox13);
        SAT = (CheckBox)findViewById(R.id.checkBox14);

        Button button = (Button) findViewById(R.id.button) ;
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AlarmListTest.class);

                TimePicker timeselect = (TimePicker)findViewById(R.id.timePicker);
                final int hour = timeselect.getHour();
                final int minute = timeselect.getMinute();


                Spinner admin_spinner = (Spinner)findViewById(R.id.spinner);
                String admin  = admin_spinner.getSelectedItem().toString();

                Spinner way_spinner = (Spinner)findViewById(R.id.spinner2);
                String way = way_spinner.getSelectedItem().toString();

                String days = "요일 :  ";

                if(SUN.isChecked() == true)
                    days = days.concat(" 일");
                if(MON.isChecked() == true)
                    days = days.concat(" 월");
                if(TUE.isChecked() == true)
                    days =days.concat(" 화");
                if(WED.isChecked() == true)
                    days = days.concat(" 수");
                if(THU.isChecked() == true)
                    days = days.concat(" 목");
                if(FRI.isChecked() == true)
                    days = days.concat(" 금");
                if(SAT.isChecked() == true)
                    days = days.concat(" 토");

                Log.d("aaaaa", String.valueOf(SUN.isChecked()));

                intent.putExtra("hour",hour);
                intent.putExtra("minute",minute);

                intent.putExtra("day",days);
                intent.putExtra("admin",admin);
                intent.putExtra("way",way);

           //     Toast.makeText(AlarmAddActivity.this,days,Toast.LENGTH_SHORT).show();

                Log.d("ddddd","aaa");
                calendar = Calendar.getInstance();
                //calendar.setTimeInMillis(System.currentTimeMillis());

                calendar.set(Calendar.YEAR,2018);
                calendar.set(Calendar.MONTH,8);
                calendar.set(Calendar.DATE,12);
                calendar.set(Calendar.HOUR_OF_DAY,hour);
                calendar.set(Calendar.MINUTE,minute);
                calendar.set(Calendar.SECOND,0);

                mAlarmIntent = new Intent(getApplicationContext(), StartReceiver.class);
                mAlarmIntent.putExtra("FLAG",0);
                Log.d("ddddd","asdf");
                mPendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,mAlarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        mPendingIntent
                );



                setResult(0,intent);
                finish();
            }
        });
    }
}
