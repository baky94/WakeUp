package com.example.baky.wakeup.View.Fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.baky.wakeup.R;
import com.example.baky.wakeup.View.Calendar.MonthAdapter;

public class AlarmTab extends Fragment {
   Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_tab,container,false);
        btn = (Button) view.findViewById(R.id.imageadd);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("dd","asdf");
                Intent intent = new Intent(getActivity(),mainLayout.class);
                startActivityForResult(intent,0);
            }
        });

        Intent receive_intent = new mainLayout().getIntent();



        return view;
    }
}