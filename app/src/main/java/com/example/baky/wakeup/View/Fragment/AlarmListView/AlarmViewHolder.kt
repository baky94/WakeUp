package com.example.baky.wakeup.View.Fragment.AlarmListView

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.baky.wakeup.R

//View뒤에 ?붙으면 itemView에 !! 붙히기
class AlarmViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var alarmTime: TextView = itemView!!.findViewById(R.id.item_alarm_time) as TextView;

    var alarmDate: TextView = itemView!!.findViewById(R.id.item_alarm_date) as TextView;
}