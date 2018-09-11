package com.example.baky.wakeup.View.Calendar;

import java.util.Date;

public class CalendarData {
    public  double latitude;
    public double longitude;
    public Date date;
    public CalendarData(double latitude, double longitude, Date date){
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }
}