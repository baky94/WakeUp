package com.example.baky.wakeup.View.Fragment;

        import android.content.Intent;
        import android.os.Bundle;
        import android.app.Activity;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.ImageButton;
        import android.widget.Spinner;
        import android.widget.TimePicker;

        import com.example.baky.wakeup.R;
        import com.example.baky.wakeup.View.MainActivity;

public class mainLayout extends Activity {

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


        TimePicker timeselect = (TimePicker)findViewById(R.id.timePicker);
        int hour = timeselect.getHour();
        int minute = timeselect.getMinute();

        CheckBox SUN,MON,TUE,WED,THU,FRI,SAT;
        SUN = (CheckBox)findViewById(R.id.checkBox8);
        MON = (CheckBox)findViewById(R.id.checkBox9);
        TUE = (CheckBox)findViewById(R.id.checkBox10);
        WED = (CheckBox)findViewById(R.id.checkBox11);
        THU = (CheckBox)findViewById(R.id.checkBox12);
        FRI = (CheckBox)findViewById(R.id.checkBox13);
        SAT = (CheckBox)findViewById(R.id.checkBox14);

        boolean a,b,c,d,e,f,g;
        if(SUN.isChecked() == true)
            a = true;
        if(MON.isChecked() == true)
            b = true;
        if(TUE.isChecked() == true)
            c = true;
        if(WED.isChecked() == true)
            d = true;
        if(THU.isChecked() == true)
            e = true;
        if(FRI.isChecked() == true)
            f = true;
        if(SAT.isChecked() == true)
            g = true;



    }

}
