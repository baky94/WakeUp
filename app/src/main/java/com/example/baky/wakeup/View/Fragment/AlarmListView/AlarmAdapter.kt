package com.example.baky.wakeup.View.Fragment.AlarmListView

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baky.wakeup.R

class AlarmAdapter(private var alarmItems : ArrayList<AlarmData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is AlarmViewHolder) {
            holder.alarmTime.text = alarmItems[position].time
            holder.alarmDate.text = alarmItems[position].date
            holder.alarmWay.text = alarmItems[position].way
            holder.alarmAdmin.text = alarmItems[position].admin
            holder.alarmDays.text = alarmItems[position].days
        }

    }

    private lateinit var onItemClick: View.OnClickListener


    fun setOnItemClickListener(l: View.OnClickListener){
        onItemClick = l;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            val mainView: View = LayoutInflater.from(parent!!.context)
                    .inflate(R.layout.alarm_item, parent, false)
            mainView.setOnClickListener(onItemClick);
            return AlarmViewHolder(mainView)

    }

    override fun getItemCount(): Int = alarmItems.size


}