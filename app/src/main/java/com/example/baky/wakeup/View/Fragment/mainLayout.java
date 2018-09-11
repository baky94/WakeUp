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
        final int hour = timeselect.getHour();
        final int minute = timeselect.getMinute();

        CheckBox SUN,MON,TUE,WED,THU,FRI,SAT;
        SUN = (CheckBox)findViewById(R.id.checkBox8);
        MON = (CheckBox)findViewById(R.id.checkBox9);
        TUE = (CheckBox)findViewById(R.id.checkBox10);
        WED = (CheckBox)findViewById(R.id.checkBox11);
        THU = (CheckBox)findViewById(R.id.checkBox12);
        FRI = (CheckBox)findViewById(R.id.checkBox13);
        SAT = (CheckBox)findViewById(R.id.checkBox14);

        final boolean[] day= new boolean[7];
        for(int i = 0; i<7; i++)
            day[i] = false;


        boolean a=false,b=false,c=false,d=false,e=false,f=false,g=false;
        if(SUN.isChecked() == true)
            day[0] = true;
        if(MON.isChecked() == true)
            day[1] = true;
        if(TUE.isChecked() == true)
            day[2] = true;
        if(WED.isChecked() == true)
            day[3] = true;
        if(THU.isChecked() == true)
            day[4] = true;
        if(FRI.isChecked() == true)
            day[5] = true;
        if(SAT.isChecked() == true)
            day[6] = true;


        Spinner admin_spinner = (Spinner)findViewById(R.id.spinner);
        final String admin  = admin_spinner.getSelectedItem().toString();

        Spinner way_spinner = (Spinner)findViewById(R.id.spinner2);
        final String way = way_spinner.getSelectedItem().toString();

        /*
        String admin_thirty = new String("30분전");
        String way_bell = new String("벨");
        int admin_thirty_result = 0;
        int way_bell_result = 0;

        if(admin_thirty.equals(admin))
            admin_thirty_result = 1;
        if(way_bell.equals(way))
            way_bell_result = 1;
*/

        Button button = (Button) findViewById(R.id.button) ;
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AlarmTab.class);

                intent.putExtra("hour",hour);
                intent.putExtra("minute",minute);

                intent.putExtra("day",day);
                intent.putExtra("admin",admin);
                intent.putExtra("way",way);

                setResult(0,intent);
                finish();
            }
        });


    }

}
