package com.example.baky.wakeup.View;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baky.wakeup.R;
import com.example.baky.wakeup.Util.AlarmReceiver;
import com.example.baky.wakeup.Util.StartReceiver;

public class ResultActivity extends AppCompatActivity {

    private TextView timeText;
    private TextView totalText;
    private TextView weatherText;
    private Button finishBtn;

    private Vibrator vibe;

    int flag;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);

        timeText = (TextView)findViewById(R.id.Result_time);
        totalText = (TextView)findViewById(R.id.total_time);
        weatherText = (TextView)findViewById(R.id.weather);
        finishBtn = (Button)findViewById(R.id.finish_btn);

        flag = this.getIntent().getIntExtra("FLAG",0);

        if(flag == 0){
            String sky = this.getIntent().getStringExtra("sky");
            String rain = this.getIntent().getStringExtra("pty");
            timeText.setVisibility(View.INVISIBLE);
            if(rain.equals("없음")) {
                weatherText.setText("오늘의 날씨는 "+sky+"이고 비나 눈은 오지 않습니다.");
            } else {
                weatherText.setText("오늘의 날씨는 "+sky+"이고 "+rain+"이 내리겠습니다.");
            }

        } else if(flag == 1){
            int time = this.getIntent().getIntExtra("time",0);
            time = time/60;
            Log.d("ddddTime",time+"");
           String sky = this.getIntent().getStringExtra("sky");
           String rain = this.getIntent().getStringExtra("pty");
            timeText.setText("목적지까지"+time+"분 소요");
            timeText.setVisibility(View.VISIBLE);
            if(rain.equals("없음")) {
                weatherText.setText("오늘의 날씨는 "+sky+"이고 비나 눈은 오지 않습니다.");
            } else {
                weatherText.setText("오늘의 날씨는 "+sky+"이고 "+rain+"이 내리겠습니다.");
            }
        }

        vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(new long[]{100,1000,100,500,100,500,100,1000},0);

       finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.cancel();

                AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getApplicationContext(), StartReceiver.class);
                PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                if (sender != null) { am.cancel(sender); sender.cancel();}
                finish();
            }
        });
    }

}
