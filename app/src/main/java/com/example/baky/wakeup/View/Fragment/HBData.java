package com.example.baky.wakeup.View.Fragment;

public class HBData {

    String time;
    String hrtbt;

    public HBData(String hrtbt, String time)
    {
        this.time = time;
        this.hrtbt = hrtbt;
    }

    public String getTime()
    {
        return time;
    }

    public String getHrtbt()
    {
        return hrtbt;
    }
}
