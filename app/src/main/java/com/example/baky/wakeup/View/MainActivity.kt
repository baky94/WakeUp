package com.example.baky.wakeup.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View
import com.example.baky.wakeup.R
import com.example.baky.wakeup.View.Fragment.*
import com.example.baky.wakeup.View.Fragment.AlarmListView.AlarmAdapter
import com.example.baky.wakeup.View.Fragment.AlarmListView.AlarmData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_alarm_list.*
import android.Manifest.permission
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.INTERNET
import java.util.jar.Manifest


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var alarmItems : ArrayList<AlarmData>

    override fun onClick(p0: View?) {
        when(p0){
            btn_main_alarm ->{
                replaceFragment(AlarmListTest())
                //현우 바보
            }

            btn_main_calendar -> {
                replaceFragment(CalendarTab())
            }

            btn_main_graph -> {
                replaceFragment(GraphTab())
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissionCheck1 = ContextCompat.checkSelfPermission(this,android.Manifest.permission.INTERNET)
        if (permissionCheck1 == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.INTERNET), 1)

        val permissionCheck2 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
        if (permissionCheck2 == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION), 1)

        val permissionCheck3 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        if (permissionCheck3 == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)

        addFragment(AlarmListTest())

        btn_main_alarm.setOnClickListener(this)
        btn_main_graph.setOnClickListener(this)
        btn_main_calendar.setOnClickListener(this)





    }


    //프래그먼트를 붙히는 함수
    fun addFragment(fragment: android.support.v4.app.Fragment) : Unit{
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.main_frame, fragment)
        transaction.commit();

    }

    fun replaceFragment(fragment: android.support.v4.app.Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.main_frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit();
    }
}
