package com.example.baky.wakeup.View.Fragment

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.baky.wakeup.R
import com.example.baky.wakeup.Util.ApplicationController
import com.example.baky.wakeup.View.Fragment.AlarmListView.AlarmAdapter
import com.example.baky.wakeup.View.Fragment.AlarmListView.AlarmAddActivity
import com.example.baky.wakeup.View.Fragment.AlarmListView.AlarmData
import com.example.baky.wakeup.View.MainActivity
import kotlinx.android.synthetic.main.fragment_alarm_list.*
import kotlinx.android.synthetic.main.fragment_alarm_list.view.*

class AlarmList : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v){
            alarm_add->{
                val intent : Intent = Intent(context, AlarmAddActivity::class.java)
                startActivityForResult(intent,0)
            }
        }
    }


    lateinit var alarmAdapter: AlarmAdapter
    lateinit var alarmRecycler: RecyclerView
    lateinit var alarmAdd : TextView
    lateinit var applicationController: ApplicationController
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_alarm_list, container, false)

        var alarmItems : ArrayList<AlarmData>?

//        alarmItems.add(AlarmData("06:00", "2018년 9월 6일","test1"))
//        alarmItems.add(AlarmData("07:00", "매일","test2"))
       // applicationController = applicationController!!.instance
        applicationController = activity!!.applicationContext as ApplicationController
        alarmItems = applicationController.alarmList

        alarmAdapter = AlarmAdapter(alarmItems)
        alarmAdapter.setOnItemClickListener(View.OnClickListener {
            //버튼 이벤트

        })

        alarmRecycler = view.findViewById(R.id.alarm_rv);
        alarmAdd = view.findViewById(R.id.alarm_add);


        alarmRecycler!!.layoutManager = LinearLayoutManager(activity)
        alarmRecycler!!.adapter = alarmAdapter

        alarmAdd.setOnClickListener(this)

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var time : Int
        var date : Int
        var way : String
        var admin : String
        var days : String
        if(resultCode == 0){
            time = data!!.getIntExtra("hour",0)
            date = data!!.getIntExtra("minute",0)
            way = data!!.getStringExtra("way")
            admin = data!!.getStringExtra("admin")
            days = data!!.getStringExtra("day")
            Log.d("ddddd",time.toString()+" : "+date+" : "+way+" :   "+days )

            //alarmItems.add(AlarmData(time.toString(),date.toString(),test))
            ApplicationController()!!.setAlarmList(AlarmData(time.toString(),date.toString(),way.toString(),admin.toString(),days.toString()))

            var ft : FragmentTransaction = fragmentManager!!.beginTransaction()
            ft.detach(this).attach(this).commit()
        }
    }
}