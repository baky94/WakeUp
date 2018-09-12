package com.example.baky.wakeup.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baky.wakeup.R;
import com.example.baky.wakeup.Util.ApplicationController;
import com.example.baky.wakeup.View.Fragment.AlarmListView.AlarmAdapter;
import com.example.baky.wakeup.View.Fragment.AlarmListView.AlarmAddActivity;
import com.example.baky.wakeup.View.Fragment.AlarmListView.AlarmData;

import java.util.ArrayList;

public class AlarmListTest extends Fragment implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.alarm_add){
            Intent intent = new Intent(getActivity().getApplicationContext(), AlarmAddActivity.class);
            startActivityForResult(intent,0);
        }
    }

    private ArrayList<AlarmData> alarmitems;
    private ApplicationController applicationController;
    private AlarmAdapter alarmAdapter;
    private RecyclerView alarmRecycler;
    private TextView alarmAdd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_list,container,false);

        // applicationController = applicationController!!.instance
        applicationController = (ApplicationController)getActivity().getApplicationContext();
        alarmitems = applicationController.AlarmList;

        alarmAdapter = new AlarmAdapter(alarmitems);
        alarmAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("dddd",view.getId()+"");
            }
        });

        alarmRecycler = view.findViewById(R.id.alarm_rv);
        alarmAdd = (TextView)view.findViewById(R.id.alarm_add);


        //alarmRecycler.setLayoutManager(LinearLayoutManager(activity))
        alarmRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        alarmRecycler.setAdapter(alarmAdapter);

        alarmAdd.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int time;
        int date;
        String way;
        String admin;
        String days;
        if(resultCode == 0){
            time = data.getIntExtra("hour",0);
            date = data.getIntExtra("minute",0);
            way = data.getStringExtra("way");
            admin = data.getStringExtra("admin");
            days = data.getStringExtra("day");
            Log.d("ddddd",time+" : "+date+" : "+way+" : "+admin);

            //alarmItems.add(AlarmData(time.toString(),date.toString(),test))
            applicationController.setAlarmList(new AlarmData(time+"",date+"",way,admin,days));

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        }
    }
}
