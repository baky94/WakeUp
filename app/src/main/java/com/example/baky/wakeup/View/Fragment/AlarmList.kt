package com.example.baky.wakeup.View.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.baky.wakeup.R
import com.example.baky.wakeup.View.Fragment.AlarmListView.AlarmAdapter
import com.example.baky.wakeup.View.Fragment.AlarmListView.AlarmAddActivity
import com.example.baky.wakeup.View.Fragment.AlarmListView.AlarmData
import com.example.baky.wakeup.View.MainActivity
import kotlinx.android.synthetic.main.fragment_alarm_list.*
import kotlinx.android.synthetic.main.fragment_alarm_list.view.*

class AlarmList : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v){
            alarm_edit->{

            }
            alarm_add->{
                val intent : Intent = Intent(context, AlarmAddActivity::class.java)
                startActivity(intent)

            }
        }

    }

    lateinit var alarmItems : ArrayList<AlarmData>
    lateinit var alarmAdapter: AlarmAdapter
    lateinit var alarmRecycler: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_alarm_list, container, false)


        alarmItems = ArrayList();
        alarmItems.add(AlarmData("06:00", "2018년 9월 6일"))
        alarmItems.add(AlarmData("07:00", "매일"))

        alarmAdapter = AlarmAdapter(alarmItems)
        alarmAdapter.setOnItemClickListener(View.OnClickListener {
            //버튼 이벤트

        })

        alarmRecycler = view.findViewById(R.id.alarm_rv);
        view.alarm_rv!!.layoutManager = LinearLayoutManager(activity)
        view.alarm_rv!!.adapter = alarmAdapter

        view.alarm_add.setOnClickListener(this)
        view.alarm_edit.setOnClickListener(this)

        return view
    }
}