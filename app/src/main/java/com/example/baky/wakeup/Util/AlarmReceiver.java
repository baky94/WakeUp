package com.example.baky.wakeup.Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.baky.wakeup.View.ResultActivity;

public class AlarmReceiver extends BroadcastReceiver {
    int totalTime;
    int skyValue;
    int ptyValue;
    int flag;
    String sky;
    String pty;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("dddddd","GET_LOCATION");
        flag = intent.getIntExtra("FLAG",0);
        skyValue = intent.getIntExtra("sky",0);
        ptyValue = intent.getIntExtra("pty",0);
        switch(skyValue){
            case 1:
                sky = "맑음";
                break;
            case 2:
                sky = "구름조금";
                break;

            case 3:
                sky = "구름많음";
                break;
            case 4:
                sky = "흐림";
                break;
        }
        switch(ptyValue){
            case 0:
                pty = "없음";
                break;
            case 1:
                pty = "비";
                break;
            case 2:
                pty = "비/눈";
                break;
            case 3:
                pty = "눈";
                break;
        }

        Intent result = new Intent(context, ResultActivity.class);
        if(flag == 0){
            result.putExtra("FLAG",0);
            result.putExtra("sky",sky);
            result.putExtra("pty",pty);
        } else if(flag == 1){
            result.putExtra("FLAG",1);
            totalTime = intent.getIntExtra("totalTime",0);
            result.putExtra("time",totalTime);
            result.putExtra("sky",sky);
            result.putExtra("pty",pty);
        }
        context.startActivity(result);
    }
}
