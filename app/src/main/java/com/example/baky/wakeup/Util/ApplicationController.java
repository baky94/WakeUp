package com.example.baky.wakeup.Util;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.baky.wakeup.View.Calendar.CalendarData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ApplicationController extends Application {
    ArrayList<CalendarData> calendarList;
    SharedPreferences pre;
    SharedPreferences.Editor edit;
    Gson gson;
    //  private ApplicationController instance;

    /**
     * Application 클래스를 상속받은 ApplicationController 객체는 어플리케이션에서 단 하나만 존재해야 합니다.
     * 따라서 내부에 ApplicationController 형의 instance를 만들어준 후
     * getter를 통해 자신의 instance를 가져오는 겁니다.
     */
    // ApplicationController 인스턴스 생성 및 getter 설정
    private  ApplicationController instance;

    public  ApplicationController getInstance() {
        return instance;
    }

    public  void setInstance(ApplicationController instance) {this.instance = instance;}


    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 어플이 실행되자마자 ApplicationController가 실행됩니다.
         * 자신의 instance를 생성하고 networkService를 만들어줍니다.
         */
        Log.i("MyTag", "Application 객체가 가장 먼저 실행됩니다.");
        // 인스턴스 가져오고 서비스 실행
        this.instance = this;
        this.initSharedPre();


    }

    public void initSharedPre() {
        pre = getSharedPreferences("hh", 0);
        gson = new GsonBuilder().create();
        edit = pre.edit();
        Type listType = new TypeToken<ArrayList<CalendarData>>(){}.getType();
        this.calendarList = gson.fromJson(pre.getString("calendarList",""),listType);
        if(this.calendarList == null){
            this.calendarList = new ArrayList<CalendarData>();
        }
        Log.d("ddd",this.calendarList.size()+"");
    }

    public void setJsonString(CalendarData data) {

        this.calendarList.add(data);
        Type saveType = new TypeToken<ArrayList<CalendarData>>(){}.getType();
        String json = gson.toJson(this.calendarList,saveType);

        SharedPreferences.Editor editor = pre.edit();
//        editor.clear();
//        editor.commit();
        editor.putString("calendarList",json);
        editor.commit();

    }
    public ArrayList<CalendarData> getJsonString(){

        pre = getSharedPreferences("hh",0);
        edit = pre.edit();
        Type getType = new TypeToken<ArrayList<CalendarData>>(){}.getType();
        String json = pre.getString("calendarList","");
        this.calendarList = gson.fromJson(json,getType);
        return this.calendarList;
    }
}