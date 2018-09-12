package com.example.baky.wakeup.Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.baky.wakeup.R;

public class StartReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        int flag;
        flag = intent.getIntExtra("FLAG",0);
        Log.d("ddddd","flag:"+flag);
        if(flag == 0){
            Intent mServiceIntent = new Intent(context,AlarmService.class);
            mServiceIntent.putExtra("FLAG",flag);
            context.startService(mServiceIntent);
        } else if(flag == 1){
            Log.d("dddd","시발");
            Intent mServiceIntent = new Intent(context,CalendarService.class);
            mServiceIntent.putExtra("FLAG",flag);
            context.startService(mServiceIntent);
        }



    }
}
