package com.example.baky.wakeup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0){
            btn_main_alarm ->{
                replaceFragment(AlarmTab())
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
        addFragment(AlarmTab())

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
